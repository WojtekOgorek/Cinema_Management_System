package ogorek.wojciech.infrastructure.repository.impl;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import ogorek.wojciech.domain.model.cinema_room.repository.CinemaRoomRepository;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.infrastructure.repository.entity.CinemaRoomEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiCinemaRoomEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CinemaRoomRepositoryImpl implements CinemaRoomRepository {

    private final JdbiCinemaRoomEntityRepository jdbiCinemaRoomEntityRepository;
    @Override
    public List<CinemaRoom> findAll() {
        return jdbiCinemaRoomEntityRepository
                .findAll()
                .stream()
                .map(CinemaRoomEntity::toCinemaRoom)
                .collect(Collectors.toList());
    }

    @Override
    public List<CinemaRoom> findAllById(List<Long> ids) {
        return jdbiCinemaRoomEntityRepository
                .findAllById(ids)
                .stream()
                .map(CinemaRoomEntity::toCinemaRoom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CinemaRoom> findById(Long id) {
        return jdbiCinemaRoomEntityRepository
                .findById(id)
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    @Override
    public Optional<CinemaRoom> findLast() {
        return jdbiCinemaRoomEntityRepository
                .findLast()
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    @Override
    public Optional<CinemaRoom> add(CinemaRoom cinemaRoom) {
        return jdbiCinemaRoomEntityRepository
                .add(new CinemaRoomEntity().fromCinemaRoom(cinemaRoom))
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    @Override
    public Optional<CinemaRoom> update(CinemaRoom cinemaRoom) {
        return jdbiCinemaRoomEntityRepository
                .update(new CinemaRoomEntity().fromCinemaRoom(cinemaRoom))
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    @Override
    public Optional<CinemaRoom> delete(Long id) {
        return jdbiCinemaRoomEntityRepository
                .delete(id)
                .map(CinemaRoomEntity::toCinemaRoom);
    }

    @Override
    public boolean deleteAll() {
        return jdbiCinemaRoomEntityRepository
                .deleteAll();
    }

    @Override
    public List<CinemaRoomWithSeats> specificCinemaRoomWithSeats(Long cinemaRoomId) {
        return jdbiCinemaRoomEntityRepository
                .specificCinemaRoomWithSeats(cinemaRoomId);
    }

    @Override
    public List<CinemaRoomWithSeance> specificCinemaRoomWithSeances(Long cinemaRoomId) {
        return jdbiCinemaRoomEntityRepository
                .specificCinemaRoomWithSeances(cinemaRoomId);
    }
}
