package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.UserService;
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

    private final JsonTransformer jsonTransformer;

    private final UserService userService;

    public void initUserRouting() {

        //USER GENERAL CRUD/
        // /api/user
        path("/api/user", () -> {

            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return userService.findAllUsers();
            }, jsonTransformer);

            post("/register", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var userToAdd = jsonTransformer.fromJson(request.body(), CreateUserDto.class);
                return userService.registerUser(userToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return userService.deleteAllUsers();
            }, jsonTransformer);

            // /api/user/id/:id
            path("/id/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.findUserById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var userToUpdate = jsonTransformer.fromJson(request.body(), CreateUserDto.class);
                    return userService.updateUser(id, userToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.deleteUser(id);
                }, jsonTransformer);
            });
            //USER SPECIAL CRUD

            // /user/name&surname?name=Joe&surname=Doe
            get("/name&surname", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var paramName = request.queryParams("name");
                var paramSurname = request.queryParams("surname");
                return userService.findUserByNameAndSurname(paramName, paramSurname);
            }, jsonTransformer);

            // /api/user/id/:id
            path("/id/:id", () -> {
                //user/id/:id/favourite
                get("/favourite", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.findUserFavourites(id);
                }, jsonTransformer);

                post("/favourite", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var favToAdd = jsonTransformer.fromJson(request.body(), CreateFavDto.class);
                    return userService.addFavourite(favToAdd);
                }, jsonTransformer);

                get("/genre", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.findFavouritesUserGenre(id);
                }, jsonTransformer);

                //users/id/:id/ticket
                get("/ticket", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.findUserWithTicket(id);
                }, jsonTransformer);

                //users/id/:id/history
                get("/history", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return userService.findUserHistory(id);
                }, jsonTransformer);
            });

        });
    }
}
