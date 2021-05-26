package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
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

    private final JsonTransformer jsonTransformer;

    private final MovieService movieService;


    public void initMovieRouting() {

        // /api/movies
        path("/api/movie", () -> {

            //MOVIE GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findAllMovies();
            }, jsonTransformer);

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var movieToAdd = jsonTransformer.fromJson(request.body(), CreateMovieDto.class);
                return movieService.addMovie(movieToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.deleteAllMovies();
            }, jsonTransformer);

            // /api/movie/id/:id
            path("/id/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return movieService.findMovieById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var movieToUpdate = jsonTransformer.fromJson(request.body(), CreateMovieDto.class);
                    return movieService.updateMovie(id ,movieToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return movieService.deleteMovie(Long.parseLong(request.params("id")));
                }, jsonTransformer);
            });

            //MOVIES SPECIAL CRUD
            // /api/movie/genre?genre=Sci Fi
            get("/genre", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var name = request.queryParams("genre");
                return movieService.findMoviesByGenre(name);
            }, jsonTransformer);

            // /api/movie/title?title=Movie A
            get("/title", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var title = request.queryParams("title");
                return movieService.findMovieByName(title);
            }, jsonTransformer);

            // api/movie/:dateFrom/dateTo/:dateTo
            get("/dateFrom/:dateFrom/dateTo/:dateTo", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMoviesByEmissionDate(request.params("dateFrom"), request.params("dateTo"));
            }, jsonTransformer);

            // /api/movie/letters/:letters
            get("/letters/:letters", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return movieService.findMoviesByFirstLetters(request.params("letters"));
            }, jsonTransformer);
        });
    }
}
