package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoJsonConverter;
import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoListJsonConverter;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.CityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static spark.Spark.*;


@Component
@RequiredArgsConstructor
public class CityRouting {

    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;
    @Value("${http.response.header.content-type}")
    private String contentTypeHeaderValue;

    private final CityService cityService;

    public void initCityRoutes() {

        // /city
        path("/city", () -> {

            //CITIES GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findAllCities();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cityToAdd = new CreateCityDtoJsonConverter(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for city"));
                return cityService.addCity(cityToAdd);

            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.deleteAllCities();
            }, new JsonTransformer());

            // /city/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cityService.findCityById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var cityToUpdate = new CreateCityDtoJsonConverter(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("invalid json body for city"));
                    return cityService.updateCity(cityToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cityService.deleteCity(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            // /city/:name
            get("/:name", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findCityByName(request.params("name"));
            }, new JsonTransformer());


            //CITIES SPECIAL CRUD
            // /city/cinema
            path("/cinema", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cityService.findCitiesWithCinemas();
                }, new JsonTransformer());

                // /city/:name/cinema
                get("/:name", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cityService.findCityWithCinemasByName(request.params("name"));
                }, new JsonTransformer());
            });
            //------------------------STATISTICS-------------------------------
            // /city/mostPopular
            get("/mostPopular", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularCity();
            }, new JsonTransformer());

            get("/mostPopularMovieInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularMoviesInCities();
            }, new JsonTransformer());

            get("/mostPopularGenreInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularGenreInCities();
            }, new JsonTransformer());

            get("/avgPricePerUserInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findAvgPricePerUserInCities();
            }, new JsonTransformer());

            get("/totalPriceSumByCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findTotalPriceSumByCities();
            }, new JsonTransformer());

            get("/mostPopularDayByCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularDayByCities();
            }, new JsonTransformer());

            get("/mostPopularTicketTypeInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularTicketType();
            }, new JsonTransformer());
        });
    }
}



