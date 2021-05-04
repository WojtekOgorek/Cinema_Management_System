package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static spark.Spark.before;

@Component
@RequiredArgsConstructor
public class SecurityFilter {
    public void routes() {
        before("/*", (request, response) -> {
            System.out.println("aaa");
        });
    }

}
