package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.CinemaEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiCinemaEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbiCinemaEntityRepositoryImpl extends AbstractCrudRepository<CinemaEntity, Long> implements JdbiCinemaEntityRepository {
    public JdbiCinemaEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<CinemaWithCinemaRooms> cinemasWithCinemaRooms() {

        final String SQL = """
                select c.id, cr.id
                from cinemas c join cinema_rooms cr on c.id = cr.cinema_id
                """;

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .mapToBean(CinemaWithCinemaRooms.class)
                        .list()
        );
    }

    @Override
    public List<CinemaWithCinemaRooms> specificCinemaWithCinemaRooms(Long cinemaId) {
        final String SQL = """
                select c.id, cr.id
                from cinemas c join cinema_rooms cr on c.id = cr.cinema_id
                where c.id = :id
                """;

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", cinemaId)
                        .mapToBean(CinemaWithCinemaRooms.class)
                        .list()
        );
    }

    @Override
    public List<CinemaWithMovies> cinemasWithMovies() {
        final String SQL = """
                select c.id, m.title, s.id
                from cinemas c 
                join cities c2 on c2.id = c.city_id
                join cinema_rooms cr on c.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join movies m on m.id = s.movie_id
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .mapToBean(CinemaWithMovies.class)
                        .list());
    }

    @Override
    public List<CinemaWithMovies> specificCinemaWithMovies(Long cinemaId) {
        final String SQL = """
                select c.id, m.title, s.id
                from cinemas c 
                join cities c2 on c2.id = c.city_id
                join cinema_rooms cr on c.id = cr.cinema_id
                join seances s on cr.id = s.cinema_room_id
                join movies m on m.id = s.movie_id
                where c.id = :id
                """;

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", cinemaId)
                        .mapToBean(CinemaWithMovies.class)
                        .list());
    }

    @Override
    public List<CinemaWithSeances> specificCinemaWithSeances(Long cinemaId) {
        final String SQL = """
                select c.id,
                        s.id
                       from seances s 
                       join cinema_rooms cr on cr.id = s.cinema_room_id 
                       join cinemas c on c.id = cr.cinema_id
                       where c.id = :id
                                
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", cinemaId)
                        .mapToBean(CinemaWithSeances.class)
                        .list());
    }
}
