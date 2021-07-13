package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.statistic.*;
import ogorek.wojciech.domain.model.statistic.repository.StatisticRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StatisticRepositoryImpl  implements StatisticRepository {

    protected final Jdbi jdbi;

    @Override
    public Optional<MostPopularCity> findMostPopularCity() {
        final String SQL = """
                select 
                c.name as city,
                count(t.id) as counter
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
                c.id as cityId,
                count(m.id) as movieId
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
                c.id as cityId,
                m.genre as genre
                from cities c 
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join movies m on m.id = s.movie_id
                group by c.id
                order by count(m.genre) desc
                """;

        return jdbi
                .withHandle(handle -> handle
                        .createQuery(SQL)
                        .mapToBean(MostPopularGenreInCities.class)
                        .list());

    }

    @Override
    //todo checkit
    public List<AvgPricePerUserInCities> findAvgPricePerUserInCities() {
        final String SQL = """
                select
                c.id as cityId,
                avg(t.price) as price
                from cities c
                join cinemas c2 on c.id = c2.city_id
                join cinema_rooms cr on c2.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join tickets t on s.id = t.seance_id
                group by c.id
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
                c.id as cityId,
                sum(t.price) as price
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
    //todo check it
    public List<MostPopularDayByCities> findMostPopularDayByCities() {
        final String SQL = """
                select
                s.date_time,
                c.id as cityId  
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
                c.id as cityId,
                t.state as state
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
