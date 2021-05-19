package ogorek.wojciech.infrastructure.web.routing;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.converter.CreateCinemaDtoJsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.domain.model.cinema_room.dto.converter.CreateCinemaRoomDtoJsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
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

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void initCinemaRoomRoutes() {

        // /cinemaRoom
        path("/api/cinemaRoom", () -> {

            //CINEMA ROOM GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findAllCinemaRooms();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
//                var cinemaRoomToAdd = new CreateCinemaRoomDtoJsonConverter(request.body())
//                        .fromJson()
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinemaRoom add"));
                var cinemaRoomToAdd = gson.fromJson(request.body(), CreateCinemaRoomDto.class);
                return cinemaRoomService.addCinemaRoom(cinemaRoomToAdd);

            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.deleteAllCinemaRooms();
            }, new JsonTransformer());

            // /cinemaRoom/:id
            path("/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaRoomService.findCinemaRoomById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
//                    var cinemaRoomToUpdate = new CreateCinemaRoomDtoJsonConverter(request.body())
//                            .fromJson()
//                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinemaRoom update"));
                    var cinemaRoomToUpdate = gson.fromJson(request.body(), CreateCinemaRoomDto.class);
                    return cinemaRoomService.updateCinemaRoom(cinemaRoomToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaRoomService.deleteCinemaRoom(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            // /cinemaRoom/:id/seat
            get("/:id/seat", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findOneCinemaRoomWithSeats(Long.parseLong(request.params("id")));
            }, new JsonTransformer());

            // /cinemaRoom/:id/seance
            get("/:id/seance", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findOneCinemaRoomWithSeances(Long.parseLong(request.params("id")));
            }, new JsonTransformer());

        });
    }
}
