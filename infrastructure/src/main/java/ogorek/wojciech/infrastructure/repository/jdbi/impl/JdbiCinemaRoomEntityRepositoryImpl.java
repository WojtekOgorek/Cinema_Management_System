package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.CinemaRoomEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiCinemaRoomEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbiCinemaRoomEntityRepositoryImpl extends AbstractCrudRepository<CinemaRoomEntity, Long> implements JdbiCinemaRoomEntityRepository {

    public JdbiCinemaRoomEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<CinemaRoomWithSeats> specificCinemaRoomWithSeats(Long cinemaRoomId) {
        final String SQL = """
                select
                cr.id as cinemaRoomId,
                s.id as seatId
                from cinema_rooms cr
                join seats s on cr.id = s.cinema_room_id
                where cr.id = :id
                """;

        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", cinemaRoomId)
                .mapToBean(CinemaRoomWithSeats.class)
                .list());
    }

    @Override
    public List<CinemaRoomWithSeance> specificCinemaRoomWithSeances(Long cinemaRoomId) {
        final String SQL = """
                    select cr.id as cinemaRoomId,
                           s.id as seanceId
                           from cinema_rooms cr
                           join seances s on cr.id = s.cinema_room_id
                           where cr.id = :id
                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", cinemaRoomId)
                        .mapToBean(CinemaRoomWithSeance.class)
                        .list()
        );
    }
}
