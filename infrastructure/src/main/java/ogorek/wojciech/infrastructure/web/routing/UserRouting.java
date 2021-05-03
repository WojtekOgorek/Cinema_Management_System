package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import ogorek.wojciech.domain.model.favourite.dto.converter.CreateFavouriteDtoJsonConverter;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.security.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class UserRouting {


    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value")
    private String contentTypeHeaderValue;

    private final UserService userService;

    public void initUserRouting() {

        //USER GENERAL CRUD/
        // /user
        path("/user", () -> {

            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return userService.findAllUsers();
            }, new JsonTransformer());

            post("/register", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var userToAdd = new JsonConverter<CreateUserDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for user registration"));
                return userService.registerUser(userToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return userService.deleteAllUsers();
            }, new JsonTransformer());

            // /user/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.findUserById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var userToUpdate = new JsonConverter<CreateUserDto>(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for user update"));
                    return userService.updateUser(userToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.deleteUser(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });
            //USER SPECIAL CRUD

            // /user/:name/:surname
            get("/:name/:surname", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return userService.findUserByNameAndSurname(request.params("name"), request.params("surname"));
            }, new JsonTransformer());

            //user/:id

            path("/:id", () -> {
                //users/:id/favourite
                get("/favourite", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.findUserFavourites(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
                post("/favourite", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var favToAdd = new CreateFavouriteDtoJsonConverter(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for favourite add"));
                    return userService.addFavourite(favToAdd);
                }, new JsonTransformer());
                get("/genre", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.findFavouritesUserGenre(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
                //users/:id/ticket
                get("/ticket", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.findUserWithTicket(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                //users/:id/history
                get("/history", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return userService.findUserHistory(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

            });

        });
    }
}
