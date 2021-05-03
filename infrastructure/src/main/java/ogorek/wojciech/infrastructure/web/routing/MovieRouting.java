package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
import ogorek.wojciech.domain.model.movie.dto.converter.CreateMovieDtoJsonConverter;
import ogorek.wojciech.service.services.cinema.MovieService;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.path;
import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class MovieRouting {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value}")
    private String contentTypeHeaderValue;

    private final MovieService movieService;


    public void initMovieRouting() {

        // /movies
        path("/movie", () -> {

            //MOVIE GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findAllMovies();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var movieToAdd = new CreateMovieDtoJsonConverter(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalStateException("Invalid json body for movie add"));
                return movieService.addMovie(movieToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.deleteAllMovies();
            }, new JsonTransformer());

            // /movie/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return movieService.findMovieById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var movieToUpdate = new CreateMovieDtoJsonConverter(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for movie update"));
                    return movieService.updateMovie(movieToUpdate);

                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return movieService.deleteMovie(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            //MOVIES SPECIAL CRUD
            // /movie/:genre
            get("/:genre", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var movie = movieService.findMoviesByGenre(request.params("genre"));
                return movie;
            }, new JsonTransformer());

            // /movie/:title
            get("/:title", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMovieByName(request.params("title"));
            }, new JsonTransformer());

            // /movie/:dateFrom/:dateTo
            get("/:dateFrom/:dateTo", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMoviesByEmissionDate(request.params("dateFrom"), request.params("dateTo"));
            }, new JsonTransformer());

            // /movie/:byLetter
            get("/:byLetter", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                System.out.println(request.params("letter"));
                return movieService.findMoviesByFirstLetters(request.params("byLetter"));
            }, new JsonTransformer());
        });
    }
}
