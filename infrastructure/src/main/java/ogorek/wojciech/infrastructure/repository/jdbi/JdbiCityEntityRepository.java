package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;
import ogorek.wojciech.infrastructure.repository.entity.CityEntity;

import java.util.List;
import java.util.Optional;

public interface JdbiCityEntityRepository extends CrudRepository<CityEntity, Long> {

    Optional<CityEntity> findCityByName(String cityName);

    List<CityWithCinemas> citiesWithCinemas();

    List<CityWithCinemas> findCityWithCinemasByName(String cityName);
}
