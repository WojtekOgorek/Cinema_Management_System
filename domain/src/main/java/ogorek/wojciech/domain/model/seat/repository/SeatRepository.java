package ogorek.wojciech.domain.model.seat.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.seat.Seat;
import ogorek.wojciech.domain.model.seat.views.SeatWithState;

import java.util.Optional;

public interface SeatRepository extends CrudRepository<Seat, Long> {
    Optional<SeatWithState> getSeatWithState(Long seatId);

}
