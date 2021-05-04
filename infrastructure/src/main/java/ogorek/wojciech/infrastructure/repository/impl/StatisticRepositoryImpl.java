package ogorek.wojciech.infrastructure.repository.impl;

import ogorek.wojciech.domain.model.city.City;
import ogorek.wojciech.domain.model.statistic.*;
import ogorek.wojciech.domain.model.statistic.repository.StatisticRepository;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//todo check it
public class StatisticRepositoryImpl extends AbstractCrudRepository<City, Long> implements StatisticRepository {
    public StatisticRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public Optional<MostPopularCity> findMostPopularCity() {
        final String SQL = """
                select 
                c.name,
                count(t.id)
                from cities c
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join tickets t on s.id = t.seance_id
                group by c.name
                order by count(t.id) desc
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .mapToBean(MostPopularCity.class)
                .findFirst());
    }

    @Override
    public List<MostPopularMovieInCities> findMostPopularMovieInCity() {
        final String SQL = """
                select
                c.id,
                count(m.id)
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join movies m on m.id = s.movie_id
                group by c.name
                order by count(m.id) desc
                """;

        return jdbi
                .withHandle(handle -> handle
                .createQuery(SQL)
                .mapToBean(MostPopularMovieInCities.class)
                .list());
    }

    @Override
    public List<MostPopularGenreInCities> findMostPopularGenreInCity() {
        final String SQL = """
                select
                c.id,
                count(m.genre)
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join movies m on m.id = s.movie_id
                group by c.name
                order by count(m.genre) desc
                """;

        return jdbi
                .withHandle(handle -> handle
                        .createQuery(SQL)
                        .mapToBean(MostPopularGenreInCities.class)
                        .list());

    }

    @Override
    public List<AvgPricePerUserInCities> findAvgPricePerUserInCities() {
        final String SQL = """
                select
                c.name,
                avg(t.price)
                from cities c
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join tickets t on s.id = t.seance_id
                group by c.name
                order by avg(t.price) desc
                """;

        return jdbi.withHandle(handle -> handle
        .createQuery(SQL)
        .mapToBean(AvgPricePerUserInCities.class)
        .list());
    }

    @Override
    public List<TotalPriceSumByCities> findTotalPriceSumByCities() {
        final String SQL = """
                select
                c.id,
                sum(t.price)
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join tickets t on s.id = t.seance_id
                group by c.id
                order by sum(t.price) desc
                """;

        return jdbi.withHandle(handle -> handle
        .createQuery(SQL)
        .mapToBean(TotalPriceSumByCities.class)
        .list());
    }


    @Override
    public List<MostPopularDayByCities> findMostPopularDayByCities() {
        final String SQL = """
                select
                s.date_time,
                c.id,
                count(t.id)     
                from tickets t 
                join seances s on s.id = t.seance_id
                join cinema_rooms cr on cr.id = s.cinema_room_id
                join cinemas c2 on c2.id = cr.cinema_id
                join cities c on c.id = c2.city_id
                group by c.id and s.date_time
                order by count(t.id) 
                """;

        return jdbi.withHandle(handle -> handle
        .createQuery(SQL)
        .mapToBean(MostPopularDayByCities.class)
        .list());
    }


    @Override
    public List<MostPopularTicketTypeInCities> findMostPopularTicketByCities() {
        final String SQL = """
                select
                c.id,
                count(t.state)
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join tickets t on s.id = t.seance_id
                group by c.id
                order by count(t.state) desc
                """;

        return jdbi.withHandle(handle -> handle
        .createQuery(SQL)
        .mapToBean(MostPopularTicketTypeInCities.class)
        .list());
    }
}
