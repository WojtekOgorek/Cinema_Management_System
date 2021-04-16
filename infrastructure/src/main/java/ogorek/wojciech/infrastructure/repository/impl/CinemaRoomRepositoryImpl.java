package ogorek.wojciech.infrastructure.repository.impl;


import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import ogorek.wojciech.domain.model.cinema_room.repository.CinemaRoomRepository;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaRoomRepositoryImpl extends AbstractCrudRepository<CinemaRoom, Long> implements CinemaRoomRepository {
    public CinemaRoomRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }



    @Override
    public List<CinemaRoomWithSeats> specificCinemaRoomWithSeats(Long cinemaRoomId) {
        final String SQL = """
                select cr.id,
                       s.id,         
                       t.state
                from cinema_rooms cr
                         join seats s on cr.id = s.cinema_room_id
                         join tickets t on s.id = t.seat_id
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
                    select cr.id,
                           s.id
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
