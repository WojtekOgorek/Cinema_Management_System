package ogorek.wojciech.infrastructure.web.routing;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.infrastructure.web.routing.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.CinemaRoomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.path;
import static spark.Spark.*;


@Component
@RequiredArgsConstructor
public class CinemaRoomRouting {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value}")
    private String contentTypeHeaderValue;

    private final CinemaRoomService cinemaRoomService;

    public void initCinemaRoomRoutes() {

        // /cinemaRooms
        path("/cinemaRooms", () -> {

            //CINEMA ROOMS GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findAllCinemaRooms();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cinemaRoomToAdd = new JsonConverter<CreateCinemaRoomDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinemaRoom add"));
                return cinemaRoomService.addCinemaRoom(cinemaRoomToAdd);

            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.deleteAllCinemaRooms();
            }, new JsonTransformer());

            // /cinemaRooms/:id
            path("/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaRoomService.findCinemaRoomById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var cinemaRoomToUpdate = new JsonConverter<CreateCinemaRoomDto>(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinemaRoom update"));
                    return cinemaRoomService.updateCinemaRoom(cinemaRoomToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaRoomService.deleteCinemaRoom(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            // /cinemaRooms/seats/:id
            get("/seats/:id", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findOneCinemaRoomWithSeats(Long.parseLong(request.params("id")));
            }, new JsonTransformer());

            // /cinemaRoom/seances/:id
            get("/seances/:id", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findOneCinemaRoomWithSeances(Long.parseLong(request.params("id")));
            }, new JsonTransformer());

        });
    }
}
