package ogorek.wojciech.infrastructure;

import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ogorek.wojciech")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppSpringConfig {


    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String databaseUsername;

    @Value("${db.password}")
    private String databasePassword;


    @Bean
    public Jdbi jdbi() {
        return Jdbi.create(databaseUrl, databaseUsername, databasePassword);
    }


}
