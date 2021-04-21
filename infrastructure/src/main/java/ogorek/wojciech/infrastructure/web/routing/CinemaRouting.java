package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.infrastructure.web.routing.transformer.JsonTransformer;
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

    private final CinemaService cinemaService;

    private final Gson gson;


    public void initCinemaRoutes() {

        // /cinemas
        path("/cinemas", () -> {

            //CINEMAS GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findAllCinemas();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cinemaToAdd = new JsonConverter<CreateCinemaDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinema add"));
                return cinemaService.addCinema(cinemaToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.deleteAllCinemas();
            }, new JsonTransformer());
            // /cinemas/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findCinemaById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var cinemaToUpdate = new JsonConverter<CreateCinemaDto>(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinema update"));
                    return cinemaService.updateCinema(cinemaToUpdate);
                }, new JsonTransformer());
                delete("/:id", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.deleteCinema(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            // /cinemas/cinemaRooms
            path("/cinemaRooms", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findCinemasWithCinemaRooms();
                }, new JsonTransformer());

                // /cinemas/cinemaRooms/:id
                path("/:id", () -> {
                    get("", (request, response) -> {
                        response.header(contentTypeHeader, contentTypeHeaderValue);
                        return cinemaService.findOneCinemaWithCinemaRooms(Long.parseLong(request.params("id")));
                    }, new JsonTransformer());
                });

            });
            // /cinemas/movies
            path("/movies", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findCinemasWithMovies();
                }, new JsonTransformer());

                //  /cinemas/movies/:id
                get("/id", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findOneCinemaWithMovies(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

            });
            // /cinemas/seances/:id
            get("/seances/:id", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findOneCinemaWithSeances(Long.parseLong(request.params("id")));
            }, new JsonTransformer());
        });

    }
}
