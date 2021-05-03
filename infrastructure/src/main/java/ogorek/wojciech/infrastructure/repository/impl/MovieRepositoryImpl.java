package ogorek.wojciech.infrastructure.repository.impl;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.movie.Movie;
import ogorek.wojciech.domain.model.movie.repository.MovieRepository;
import ogorek.wojciech.infrastructure.repository.entity.MovieEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiMovieEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final JdbiMovieEntityRepository jdbiMovieEntityRepository;

    @Override
    public List<Movie> findAll() {
        return jdbiMovieEntityRepository
                .findAll()
                .stream()
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findAllById(List<Long> ids) {
        return jdbiMovieEntityRepository
                .findAllById(ids)
                .stream()
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return jdbiMovieEntityRepository
                .findById(id)
                .map(MovieEntity::toMovie);
    }

    @Override
    public Optional<Movie> findLast() {
        return jdbiMovieEntityRepository
                .findLast()
                .map(MovieEntity::toMovie);
    }

    @Override
    public Optional<Movie> add(Movie movie) {
        return jdbiMovieEntityRepository
                .add(new MovieEntity().fromMovie(movie))
                .map(MovieEntity::toMovie);
    }

    @Override
    public Optional<Movie> update(Movie movie) {
        return jdbiMovieEntityRepository
                .update(new MovieEntity().fromMovie(movie))
                .map(MovieEntity::toMovie);
    }

    @Override
    public Optional<Movie> delete(Long id) {
        return jdbiMovieEntityRepository
                .delete(id)
                .map(MovieEntity::toMovie);
    }

    @Override
    public boolean deleteAll() {
        return jdbiMovieEntityRepository
                .deleteAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return jdbiMovieEntityRepository
                .getMoviesByGenre(genre)
                .stream()
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> getMovieByName(String movieTitle) {
        return jdbiMovieEntityRepository
                .getMovieByName(movieTitle)
                .map(MovieEntity::toMovie);
    }

    @Override
    public List<Movie> getMoviesByEmissionDate(String from, String to) {
        return jdbiMovieEntityRepository
                .getMoviesByEmissionDate(from, to)
                .stream()
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByFirstLetters(String letters) {
        return jdbiMovieEntityRepository
                .getMoviesByFirstLetters(letters)
                .stream()
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }
}
