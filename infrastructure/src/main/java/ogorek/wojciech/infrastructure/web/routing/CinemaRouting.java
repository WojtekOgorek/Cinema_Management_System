package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class CinemaRouting {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value")
    private String contentTypeHeaderValue;

    private final JsonTransformer jsonTransformer;

    private final CinemaService cinemaService;


    public void initCinemaRoutes() {

        // /cinema
        path("/api/cinema", () -> {

            //CINEMAS GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findAllCinemas();
            }, jsonTransformer);

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cinemaToAdd = jsonTransformer.fromJson(request.body(), CreateCinemaDto.class);
                return cinemaService.addCinema(cinemaToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.deleteAllCinemas();
            }, jsonTransformer);
            // /api/cinema/id/:id
            path("/id/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cinemaService.findCinemaById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var cinemaToUpdate = jsonTransformer.fromJson(request.body(), CreateCinemaDto.class);
                    return cinemaService.updateCinema(id, cinemaToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cinemaService.deleteCinema(id);
                }, jsonTransformer);
            });

            // /api/cinema/cinemaRoom
            get("/cinemaRoom", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findCinemasWithCinemaRooms();
            }, new JsonTransformer());

            // /api/cinema/id/:id/cinemaRoom
            get("/id/:id/cinemaRoom", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var id = Long.parseLong(request.params("id"));
                return cinemaService.findOneCinemaWithCinemaRooms(id);
            }, jsonTransformer);

            // /api/cinema/movie

            get("/movie", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findCinemasWithMovies();
            }, jsonTransformer);

            //  /api/cinema/id/:id/movie
            get("/id/:id/movie", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var id = Long.parseLong(request.params("id"));
                return cinemaService.findOneCinemaWithMovies(id);
            }, jsonTransformer);


            // /api/cinema/id/:id/seance
            get("/id/:id/seance", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var id = Long.parseLong(request.params("id"));
                return cinemaService.findOneCinemaWithSeances(id);
            }, jsonTransformer);
        });

    }
}
