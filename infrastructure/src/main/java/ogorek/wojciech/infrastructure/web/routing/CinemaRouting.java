package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.domain.model.cinema.dto.converter.CreateCinemaDtoJsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
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

    private final CinemaService cinemaService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void initCinemaRoutes() {

        // /cinema
        path("/api/cinema", () -> {

            //CINEMAS GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findAllCinemas();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
//                var cinemaToAdd = new CreateCinemaDtoJsonConverter(request.body())
//                        .fromJson()
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinema add"));
                var cinemaToAdd = gson.fromJson(request.body(), CreateCinemaDto.class);
                return cinemaService.addCinema(cinemaToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.deleteAllCinemas();
            }, new JsonTransformer());
            // /cinema/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findCinemaById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
//                    var cinemaToUpdate = new CreateCinemaDtoJsonConverter(request.body())
//                            .fromJson()
//                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for cinema update"));
                    var cinemaToUpdate = gson.fromJson(request.body(), CreateCinemaDto.class);
                    return cinemaService.updateCinema(cinemaToUpdate);
                }, new JsonTransformer());
                delete("/:id", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.deleteCinema(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            // /cinema/cinemaRoom
            get("/cinemaRoom", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findCinemasWithCinemaRooms();
            }, new JsonTransformer());

            // /cinema/:id/cinemaRoom
            get("/:id/cinemaRoom", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findOneCinemaWithCinemaRooms(Long.parseLong(request.params("id")));
            }, new JsonTransformer());

            // /cinema/movie
            path("/movie", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findCinemasWithMovies();
                }, new JsonTransformer());

                //  /cinema/:id/movie
                get("/:id/movie", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cinemaService.findOneCinemaWithMovies(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

            });
            // /cinema/:id/seance
            get("/:id/seance", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cinemaService.findOneCinemaWithSeances(Long.parseLong(request.params("id")));
            }, new JsonTransformer());
        });

    }
}
