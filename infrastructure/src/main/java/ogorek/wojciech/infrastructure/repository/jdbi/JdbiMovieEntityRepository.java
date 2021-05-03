package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface JdbiMovieEntityRepository extends CrudRepository<MovieEntity, Long> {

    List<MovieEntity> getMoviesByGenre(String genre);
    Optional<MovieEntity> getMovieByName(String movieTitle);
    List<MovieEntity> getMoviesByEmissionDate(String dateFrom, String dateTo);
    List<MovieEntity> getMoviesByFirstLetters(String firstLetters);
}
