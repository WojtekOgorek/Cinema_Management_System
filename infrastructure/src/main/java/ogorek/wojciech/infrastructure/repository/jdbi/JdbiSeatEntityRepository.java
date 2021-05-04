package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.seat.views.SeatWithState;
import ogorek.wojciech.infrastructure.repository.entity.SeatEntity;

import java.util.Optional;

public interface JdbiSeatEntityRepository extends CrudRepository<SeatEntity, Long> {

    Optional<SeatWithState> getSeatWithState(Long seatId);
}
