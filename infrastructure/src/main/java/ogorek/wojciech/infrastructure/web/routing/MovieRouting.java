package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
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
        path("/movies", () -> {

            //MOVIE GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findAllMovies();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var movieToAdd = new JsonConverter<CreateMovieDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalStateException("Invalid json body for movie add"));
                return movieService.addMovie(movieToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.deleteAllMovies();
            }, new JsonTransformer());

            // /movies/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return movieService.findMovieById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var movieToUpdate = new JsonConverter<CreateMovieDto>(request.body())
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
            // /movies/:genre
            get("/:genre", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var movie = movieService.findMoviesByGenre(request.params("genre"));
                return movie;
            }, new JsonTransformer());

            // /movies/:title
            get("/:title", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMovieByName(request.params("title"));
            }, new JsonTransformer());

            // /movies/:date/:date2
            get("/:date/:date2", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMoviesByEmissionDate(request.params("date"), request.params("date2"));
            }, new JsonTransformer());

            // /movies/:letter
            get("/:letter", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                System.out.println(request.params("letter"));
                return movieService.findMoviesByFirstLetters(request.params("letter"));
            }, new JsonTransformer());
        });
    }
}
