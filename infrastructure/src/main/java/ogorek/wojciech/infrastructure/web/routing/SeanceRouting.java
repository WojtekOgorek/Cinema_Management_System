package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
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

    private final SeanceService seanceService;

    private final Gson gson;

    public void initSeanceRouting() {

        // /seances
        path("/seances", () -> {

            //SEANCES GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.findAllSeances();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var seanceToAdd = new JsonConverter<CreateSeanceDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for seance add"));
                return seanceService.addSeance(seanceToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.deleteAllSeances();
            }, new JsonTransformer());

            // /seances/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return seanceService.findSeanceById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {

                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var seanceToUpdate = new JsonConverter<CreateSeanceDto>(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for seance update"));
                    return seanceService.updateSeance(seanceToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return seanceService.deleteSeance(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            //SEANCES SPECIAL CRUD
            // /seances/:from/:to
            get("/:from/:to", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return seanceService.findSeancesByDate(request.params("from"), request.params("to"));
            }, new JsonTransformer());
        });
    }
}