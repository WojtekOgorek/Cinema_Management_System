package ogorek.wojciech.infrastructure.repository.impl;

import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.repository.TicketRepository;
import ogorek.wojciech.domain.model.ticket.views.TicketInfo;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl extends AbstractCrudRepository<Ticket, Long> implements TicketRepository {
    public TicketRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<Ticket> findTicketByUsername(String username) {
        final String SQL = """
                                select 
                                t.id
                                from tickets t 
                                join users u on u.id = t.user_id
                                where u.username = :username
                """;

        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("username", username)
                .mapToBean(Ticket.class)
                .list());
    }


    @Override
    public Optional<TicketInfo> findTicketInfo(Long id) {

        final String SQL = """
                select
                c.name,
                c2.name,
                cr.name,
                m.title,
                s.date_time,
                s2.seat_row,
                s2.seat_place
                from tickets t 
                join seances s on t.seance_id = s.id
                join cinema_rooms cr on s.cinema_room_id = cr.id
                join seats s2 on cr.id = s2.cinema_room_id
                join movies m on s.movie_id = m.id
                join cinemas c2 on cr.cinema_id = c2.id
                join cities c on c2.city_id = c.id;                  
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", id)
                .mapToBean(TicketInfo.class)
                .findFirst());
    }

}
