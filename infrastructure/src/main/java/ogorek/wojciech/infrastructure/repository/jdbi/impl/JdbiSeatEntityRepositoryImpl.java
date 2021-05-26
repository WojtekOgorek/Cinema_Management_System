package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.seat.views.SeatWithState;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.CityEntity;
import ogorek.wojciech.infrastructure.repository.entity.SeatEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiSeatEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbiSeatEntityRepositoryImpl extends AbstractCrudRepository<SeatEntity, Long> implements JdbiSeatEntityRepository {
    public JdbiSeatEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public Optional<SeatWithState> getSeatWithState(Long seatId) {
        final String SQL = """
                select
                s.id as seatId,
                t.state
                from seats s
                join tickets t on s.id = t.seat_id
                where s.id = :id
                """;

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("id", seatId)
                        .mapToBean(SeatWithState.class)
                        .findFirst());
    }

}
