package ogorek.wojciech.infrastructure.web.error;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class ErrorRouting {
    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;
    @Value("${http.response.header.content-type}")
    private String contentTypeHeaderValue;


    private final JsonTransformer jsonTransformer;

    public void initErrorRoutes() {

        exception(Exception.class, ((e, request, response) -> {
            e.printStackTrace();
            response.redirect("/error", 301);
        }));

        path("/error", () -> {
            get("", ((request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return jsonTransformer.render(ErrorMessage
                        .builder()
                        .message("APP EXCEPTION")
                        .build());
            }));
        });
    }
}
