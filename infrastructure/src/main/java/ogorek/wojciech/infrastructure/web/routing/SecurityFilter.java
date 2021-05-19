package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.user.enums.Role;
import ogorek.wojciech.infrastructure.security.AppTokenService;
import ogorek.wojciech.infrastructure.security.dto.AuthenticationDto;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
                    //object -> uri, request.getMethod()
                    Map.entry(Role.USER, List.of(
                            "/api/city/"
                    )),
                    Map.entry(Role.ADMIN, List.of(
                            "/api/city"
                    ))
            );

            System.out.println("--------------------------- 1 ------------------------");

            var uri = request.uri();
            System.out.println("--------------------------- 2 ------------------------");
            if (!permitAllUris.contains(uri)) {
                var header = request.headers("Authorization");
                var user = appTokenService.parseToken(header);
                var role = user.getRole();
                System.out.println("--------------------------- 3 ------------------------");
                if (!permissions.containsKey(role)) {
                    throw new IllegalStateException("Cannot find role of user");
                }
                System.out.println("--------------------------- 4 ------------------------");
                if (permissions
                        .get(role)
                        .stream()
                        .noneMatch(uri::startsWith)) {
                    throw new IllegalStateException("Access ios denied");
                }
                System.out.println("--------------------------- 5 ------------------------");
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
