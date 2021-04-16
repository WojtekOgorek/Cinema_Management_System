package ogorek.wojciech.infrastructure.repository.impl;


import ogorek.wojciech.domain.model.city.City;
import ogorek.wojciech.domain.model.city.repository.CityRepository;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepositoryImpl extends AbstractCrudRepository<City, Long> implements CityRepository {
    public CityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }


    @Override
    public Optional<City> findCityByName(String cityName) {
        final String SQL = """
                select 
                c.id, 
                c.name
                from cities c 
                where name = :name
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("name", cityName)
                        .mapToBean(City.class)
                        .findFirst());
    }

    @Override
    public List<CityWithCinemas> citiesWithCinemas() {
        final String SQL = """
                    select 
                    c.id,
                    c2.id
                    from cities c 
                    join cinemas c2 on c.id = c2.city_id
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .mapToBean(CityWithCinemas.class)
                        .list());

    }


    @Override
    public List<CityWithCinemas> findCityWithCinemasByName(String cityName) {
        final String SQL = """
                select
                c.id, 
                c2.id
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                where c.name = :name;
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("name", cityName)
                        .mapToBean(CityWithCinemas.class)
                        .list()
        );
    }


}
