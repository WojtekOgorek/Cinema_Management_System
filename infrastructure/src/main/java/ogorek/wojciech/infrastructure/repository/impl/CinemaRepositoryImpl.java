package ogorek.wojciech.infrastructure.repository.impl;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.Cinema;
import ogorek.wojciech.domain.model.cinema.repository.CinemaRepository;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;
import ogorek.wojciech.infrastructure.repository.entity.CinemaEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiCinemaEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CinemaRepositoryImpl implements CinemaRepository {

    private final JdbiCinemaEntityRepository jdbiCinemaEntityRepository;

    @Override
    public List<Cinema> findAll() {
        return jdbiCinemaEntityRepository
                .findAll()
                .stream()
                .map(CinemaEntity::toCinema)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cinema> findAllById(List<Long> ids) {
        return jdbiCinemaEntityRepository
                .findAllById(ids)
                .stream()
                .map(CinemaEntity::toCinema)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cinema> findById(Long id) {
        return jdbiCinemaEntityRepository
                .findById(id)
                .map(CinemaEntity::toCinema);
    }

    @Override
    public Optional<Cinema> findLast() {
        return jdbiCinemaEntityRepository
                .findLast()
                .map(CinemaEntity::toCinema);
    }

    @Override
    public Optional<Cinema> add(Cinema cinema) {
        return jdbiCinemaEntityRepository
                .add(new CinemaEntity().fromCinema(cinema))
                .map(CinemaEntity::toCinema);
    }

    @Override
    public Optional<Cinema> update(Cinema cinema) {
        return jdbiCinemaEntityRepository
                .update(new CinemaEntity().fromCinema(cinema))
                .map(CinemaEntity::toCinema);
    }

    @Override
    public Optional<Cinema> delete(Long id) {
        return jdbiCinemaEntityRepository
                .delete(id)
                .map(CinemaEntity::toCinema);
    }

    @Override
    public boolean deleteAll() {
        return jdbiCinemaEntityRepository
                .deleteAll();
    }

    @Override
    public List<CinemaWithCinemaRooms> specificCinemaWithCinemaRooms(Long cinemaId) {
        return jdbiCinemaEntityRepository
                .specificCinemaWithCinemaRooms(cinemaId);
    }

    @Override
    public List<CinemaWithCinemaRooms> cinemasWithCinemaRooms() {
        return jdbiCinemaEntityRepository
                .cinemasWithCinemaRooms();
    }

    @Override
    public List<CinemaWithMovies> specificCinemaWithMovies(Long cinemaId) {
        return jdbiCinemaEntityRepository
                .specificCinemaWithMovies(cinemaId);
    }

    @Override
    public List<CinemaWithMovies> cinemasWithMovies() {
        return jdbiCinemaEntityRepository
                .cinemasWithMovies();
    }

    @Override
    public List<CinemaWithSeances> specificCinemaWithSeances(Long cinemaId) {
        return jdbiCinemaEntityRepository
                .specificCinemaWithSeances(cinemaId);
    }
}

