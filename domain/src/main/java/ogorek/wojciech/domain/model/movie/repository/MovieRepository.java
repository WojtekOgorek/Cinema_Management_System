package ogorek.wojciech.domain.model.movie.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.movie.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> getMoviesByGenre(String genre);
    Optional<Movie> getMovieByName(String movieTitle);
    List<Movie> getMoviesByEmissionDate(String from, String to);
    List<Movie> getMoviesByFirstLetters(String letters);


}
