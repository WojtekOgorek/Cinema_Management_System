package ogorek.wojciech.domain.model.city.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.city.City;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {
    Optional<City> findCityByName(String cityName);
    List<CityWithCinemas> findCityWithCinemasByName(String cityName);
    List<CityWithCinemas> citiesWithCinemas();

}
