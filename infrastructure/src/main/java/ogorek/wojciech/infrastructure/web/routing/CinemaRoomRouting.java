package ogorek.wojciech.infrastructure.web.routing;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.CinemaRoomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;


@Component
@RequiredArgsConstructor
public class CinemaRoomRouting {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value}")
    private String contentTypeHeaderValue;

    private final JsonTransformer jsonTransformer;

    private final CinemaRoomService cinemaRoomService;

    public void initCinemaRoomRoutes() {

        // /api/cinemaRoom
        path("/api/cinemaRoom", () -> {

            //CINEMA ROOM GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.findAllCinemaRooms();
            }, jsonTransformer);

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cinemaRoomToAdd = jsonTransformer.fromJson(request.body(), CreateCinemaRoomDto.class);
                return cinemaRoomService.addCinemaRoom(cinemaRoomToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaRoomService.deleteAllCinemaRooms();
            }, jsonTransformer);

            // /api/cinemaRoom/id/:id
            path("/id/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cinemaRoomService.findCinemaRoomById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var cinemaRoomToUpdate = jsonTransformer.fromJson(request.body(), CreateCinemaRoomDto.class);
                    return cinemaRoomService.updateCinemaRoom(id, cinemaRoomToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cinemaRoomService.deleteCinemaRoom(id);
                }, jsonTransformer);
            });

            // /api/cinemaRoom/id/:id/seat
            get("/id/:id/seat", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var id = Long.parseLong(request.params("id"));
                return cinemaRoomService.findOneCinemaRoomWithSeats(id);
            }, jsonTransformer);

            // /api/cinemaRoom/id/:id/seance
            get("/id/:id/seance", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var id = Long.parseLong(request.params("id"));
                return cinemaRoomService.findOneCinemaRoomWithSeances(id);
            }, jsonTransformer);

        });
    }
}
