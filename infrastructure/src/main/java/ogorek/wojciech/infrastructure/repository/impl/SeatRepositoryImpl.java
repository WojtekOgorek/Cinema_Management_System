package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seat.Seat;
import ogorek.wojciech.domain.model.seat.repository.SeatRepository;
import ogorek.wojciech.domain.model.seat.views.SeatWithState;
import ogorek.wojciech.infrastructure.repository.entity.SeatEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiSeatEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

    private final JdbiSeatEntityRepository jdbiSeatEntityRepository;

    @Override
    public List<Seat> findAll() {
        return jdbiSeatEntityRepository
                .findAll()
                .stream()
                .map(SeatEntity::toSeat)
                .collect(Collectors.toList());
    }

    @Override
    public List<Seat> findAllById(List<Long> ids) {
        return jdbiSeatEntityRepository
                .findAllById(ids)
                .stream()
                .map(SeatEntity::toSeat)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return jdbiSeatEntityRepository
                .findById(id)
                .map(SeatEntity::toSeat);
    }

    @Override
    public Optional<Seat> findLast() {
        return jdbiSeatEntityRepository
                .findLast()
                .map(SeatEntity::toSeat);
    }

    @Override
    public Optional<Seat> add(Seat seat) {
        return jdbiSeatEntityRepository
                .add(new SeatEntity().fromSeat(seat))
                .map(SeatEntity::toSeat);
    }

    @Override
    public Optional<Seat> update(Seat seat) {
        return jdbiSeatEntityRepository
                .update(new SeatEntity().fromSeat(seat))
                .map(SeatEntity::toSeat);
    }

    @Override
    public Optional<Seat> delete(Long id) {
        return jdbiSeatEntityRepository
                .delete(id)
                .map(SeatEntity::toSeat);
    }

    @Override
    public boolean deleteAll() {
        return jdbiSeatEntityRepository
                .deleteAll();
    }

    @Override
    public Optional<SeatWithState> getSeatWithState(Long seatId) {
        return jdbiSeatEntityRepository
                .getSeatWithState(seatId);
    }
}
