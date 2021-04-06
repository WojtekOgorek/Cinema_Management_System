package ogorek.wojciech.domain.model.cinema.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.cinema.Cinema;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    List<CinemaWithCinemaRooms> specificCinemaWithCinemaRooms(Long cinemaId);
    List<CinemaWithCinemaRooms> cinemasWithCinemaRooms();
    List<CinemaWithMovies> specificCinemaWithMovies(Long cinemaId);
    List<CinemaWithMovies> cinemasWithMovies();
    List<CinemaWithSeances> specificCinemaWithSeances(Long cinemaId);



}
