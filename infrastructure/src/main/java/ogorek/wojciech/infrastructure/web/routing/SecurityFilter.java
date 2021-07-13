package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.user.enums.Role;
import ogorek.wojciech.infrastructure.security.AppTokenService;
import ogorek.wojciech.infrastructure.security.dto.AuthenticationDto;
import ogorek.wojciech.infrastructure.web.exception.AppRoutingException;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.infrastructure.web.utils.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class SecurityFilter {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value}")
    private String contentTypeHeaderValue;

    private final AppTokenService appTokenService;
    private final JsonTransformer jsonTransformer;

    public void initSecurityFilter() {
        before("/*", (request, response) -> {
            var permitAllUris = List.of(
                    "/api/user/register",
                    "/login"
            );
            var permissions = Map.ofEntries(

                    Map.entry(Role.USER, List.of(
                            Permission.builder().uri("/api/city").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/cinema").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/cinemaRoom").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/movie").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/seance").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/ticket/username?").getMethod(Set.of("GET")).build(),
                            Permission.builder().uri("/api/ticket/order").getMethod(Set.of("POST")).build()
                    )),
                    Map.entry(Role.ADMIN, List.of(
                            Permission.builder().uri("/api/city").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/cinema").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/cinemaRoom").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/movie").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/seance").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/seat").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/ticket").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build(),
                            Permission.builder().uri("/api/user").getMethod(Set.of("GET", "PUT", "POST", "DELETE")).build()
                    ))
            );


            var uri = request.uri();
            var methodRequest = request.requestMethod();
            if (!permitAllUris.contains(uri)) {
                var header = request.headers("Authorization");
                var user = appTokenService.parseToken(header);
                var role = user.getRole();

                if (!permissions.containsKey(role)) {
                    throw new AppRoutingException("Cannot find role of user");
                }

                if (permissions
                        .get(role)
                        .stream()
                        .map(Permission::getUri)
                        .noneMatch(uri::startsWith)) {
                    throw new AppRoutingException("Access ios denied - uri");
                }

                if (permissions
                        .get(role)
                        .stream()
                        .flatMap(list -> list.getGetMethod().stream())
                        .noneMatch(methodRequest::startsWith)) {
                    throw new AppRoutingException("Access ios denied - method");
                }
            }
        });

        path("/login", () -> {
            post("", (request, response) -> {
                var authenticationDto = jsonTransformer.fromJson(request.body(), AuthenticationDto.class);
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return appTokenService.generateTokens(authenticationDto);
            }, jsonTransformer);
        });
    }

}
