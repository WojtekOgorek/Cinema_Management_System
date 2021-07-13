package ogorek.wojciech.infrastructure.repository.impl;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.City;
import ogorek.wojciech.domain.model.city.repository.CityRepository;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;
import ogorek.wojciech.infrastructure.repository.entity.CityEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiCityEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final JdbiCityEntityRepository jdbiCityEntityRepository;


    @Override
    public List<City> findAll() {
        return jdbiCityEntityRepository
                .findAll()
                .stream()
                .map(CityEntity::toCity)
                .collect(Collectors.toList());
    }

    @Override
    public List<City> findAllById(List<Long> ids) {
        return jdbiCityEntityRepository
                .findAllById(ids)
                .stream()
                .map(CityEntity::toCity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<City> findById(Long id) {
        return jdbiCityEntityRepository
                .findById(id)
                .map(CityEntity::toCity);
    }

    @Override
    public Optional<City> findLast() {
        return jdbiCityEntityRepository
                .findLast()
                .map(CityEntity::toCity);
    }

    @Override
    public Optional<City> add(City city) {
        return jdbiCityEntityRepository
                .add(new CityEntity().fromCity(city))
                .map(CityEntity::toCity);

    }

    @Override
    public Optional<City> update(City city) {
        return jdbiCityEntityRepository
                .update(new CityEntity().fromCity(city))
                .map(CityEntity::toCity);

    }

    @Override
    public Optional<City> delete(Long id) {
        return jdbiCityEntityRepository
                .delete(id)
                .map(CityEntity::toCity);
    }

    @Override
    public boolean deleteAll() {
        return jdbiCityEntityRepository
                .deleteAll();
    }

    @Override
    public Optional<City> findCityByName(String cityName) {
        return jdbiCityEntityRepository
                .findCityByName(cityName)
                .map(CityEntity::toCity);
    }

    @Override
    public List<CityWithCinemas> citiesWithCinemas() {
        return jdbiCityEntityRepository
                .citiesWithCinemas();
    }


    @Override
    public List<CityWithCinemas> findCityWithCinemasByName(String cityName) {
        return jdbiCityEntityRepository
                .findCityWithCinemasByName(cityName);
    }
}
