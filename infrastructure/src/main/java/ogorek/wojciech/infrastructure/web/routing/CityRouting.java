package ogorek.wojciech.infrastructure.web.routing;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
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


    private final JsonTransformer jsonTransformer;

    private final CityService cityService;

    public void initCityRoutes() {

        // /city
        path("/api/city", () -> {

            //CITIES GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findAllCities();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var cityToAdd = jsonTransformer.fromJson(request.body(), CreateCityDto.class);
                return cityService.addCity(cityToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.deleteAllCities();
            }, jsonTransformer);

            // /api/city/:id
            path("/id/:id", () -> {
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cityService.findCityById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var cityToUpdate = jsonTransformer.fromJson(request.body(), CreateCityDto.class);
                    return cityService.updateCity(id, cityToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return cityService.deleteCity(id);
                }, jsonTransformer);
            });
            // /api/city/name?name=City A
            get("/name", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var name = request.queryParams("name");
                return cityService.findCityByName(name);
            }, jsonTransformer);



            //CITIES SPECIAL CRUD
            // /api/city/cinema
            path("/cinema", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return cityService.findCitiesWithCinemas();
                }, jsonTransformer);

                // /api/city/cinema/name
                get("/name", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var cityName = request.queryParams("cityName");
                    return cityService.findCityWithCinemasByName(cityName);
                }, jsonTransformer);
            });
            //------------------------STATISTICS-------------------------------
            // /api/city/mostPopular
            get("/mostPopular", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularCity();
            }, jsonTransformer);

            get("/mostPopularMovieInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularMoviesInCities();
            }, jsonTransformer);

            get("/mostPopularGenreInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularGenreInCities();
            },jsonTransformer);

            get("/avgPricePerUserInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findAvgPricePerUserInCities();
            }, jsonTransformer);

            get("/totalPriceSumByCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findTotalPriceSumByCities();
            }, jsonTransformer);

            get("/mostPopularDayByCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularDayByCities();
            }, jsonTransformer);

            get("/mostPopularTicketTypeInCities", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return cityService.findMostPopularTicketType();
            }, jsonTransformer);
        });

    }
}



