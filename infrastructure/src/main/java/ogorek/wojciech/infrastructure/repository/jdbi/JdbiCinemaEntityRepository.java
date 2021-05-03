package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;
import ogorek.wojciech.infrastructure.repository.entity.CinemaEntity;

import java.util.List;

public interface JdbiCinemaEntityRepository extends CrudRepository<CinemaEntity, Long> {

    List<CinemaWithCinemaRooms> cinemasWithCinemaRooms();
    List<CinemaWithCinemaRooms> specificCinemaWithCinemaRooms(Long cinemaId);
    List<CinemaWithMovies> cinemasWithMovies();
    List<CinemaWithMovies> specificCinemaWithMovies(Long cinemaId);
    List<CinemaWithSeances> specificCinemaWithSeances(Long cinemaId);
}
