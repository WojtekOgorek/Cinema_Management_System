package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
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

    private final SeatService seatService;


    public void initSeatRouting(){

        // /seat
        path("/seat", () -> {
            //SEATS GENERAL CRUD
            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var seatToAdd = new JsonConverter<CreateSeatDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for seat add"));
                return seatService.addSeat(seatToAdd);
            }, new JsonTransformer());
            path("/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return seatService.findSeatById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return seatService.deleteSeat(Long.parseLong(request.params("id")));
                },new JsonTransformer());
                //seat/:id/free
                get("/free", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return seatService.isSeatFree(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

        });

    }
}

