package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.SeatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class SeatRouting {


    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value")
    private String contentTypeHeaderValue;

    private final JsonTransformer jsonTransformer;

    private final SeatService seatService;



    public void initSeatRouting(){

        // /api/seat
        path("/api/seat", () -> {
            //SEATS GENERAL CRUD
            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var seatToAdd = jsonTransformer.fromJson(request.body(), CreateSeatDto.class);
                return seatService.addSeat(seatToAdd);
            }, jsonTransformer);

            path("/id/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return seatService.findSeatById(id);
                }, jsonTransformer);
                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return seatService.deleteSeat(id);
                },jsonTransformer);
                // api/seat/id/:id/free
                get("/free", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return seatService.isSeatFree(id);
                }, jsonTransformer);
            });

        });

    }
}

