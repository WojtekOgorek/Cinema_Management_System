package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.SeanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class SeanceRouting {


    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value}")
    private String contentTypeHeaderValue;

    private final JsonTransformer jsonTransformer;

    private final SeanceService seanceService;


    public void initSeanceRouting() {

        // /seances
        path("/api/seance", () -> {

            //SEANCES GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.findAllSeances();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var seanceToAdd = jsonTransformer.fromJson(request.body(), CreateSeanceDto.class);
                return seanceService.addSeance(seanceToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.deleteAllSeances();
            }, jsonTransformer);

            // /api/seance/id/:id
            path("/id/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return seanceService.findSeanceById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var seanceToUpdate = jsonTransformer.fromJson(request.body(), CreateSeanceDto.class);
                    return seanceService.updateSeance(id, seanceToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return seanceService.deleteSeance(id);
                }, jsonTransformer);
            });

            //SEANCES SPECIAL CRUD
            // /api/seances/dateFrom/:dateFrom/dateTo/:dateTo/state/:state
            get("/dateFrom/:dateFrom/dateTo/:dateTo/state/:state", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.findSeancesByDate(request.params("dateFrom"), request.params("dateTo"), request.params("state"));
            }, jsonTransformer);
        });
    }
}
