package ogorek.wojciech.infrastructure.repository.impl;

import ogorek.wojciech.domain.model.seat.Seat;
import ogorek.wojciech.domain.model.seat.repository.SeatRepository;
import ogorek.wojciech.domain.model.seat.views.SeatWithState;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SeatRepositoryImpl extends AbstractCrudRepository<Seat, Long> implements SeatRepository {
    public SeatRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public Optional<SeatWithState> getSeatWithState(Long seatId) {
        final String SQL = """
                select
                s.id,
                t.state
                from seats s
                join tickets t on seats.id = t.seat_id
                where s.id = :id
                """;

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", seatId))
                        .mapToBean(SeatWithState.class)
                        .findFirst();
    }
}
