package ogorek.wojciech.infrastructure.repository.impl;


import ogorek.wojciech.domain.model.movie.Movie;
import ogorek.wojciech.domain.model.movie.repository.MovieRepository;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl extends AbstractCrudRepository<Movie, Long> implements MovieRepository {
    public MovieRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        final String SQL = """
                    select m.id,
                    m.title,
                    m.genre,
                    m.start_date,
                    m.end_date
                    from movies m
                    where m.genre = :genre
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("genre", genre)
                .mapToBean(Movie.class)
                .list()
        );
    }

    @Override
    public Optional<Movie> getMovieByName(String movieTitle) {
        final String SQL = """
                    select m.id,
                    m.title,
                    m.genre,
                    m.start_date,
                    m.end_date
                    from movies m
                    where m.title = :title
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("title", movieTitle)
                .mapToBean(Movie.class)
                .findFirst()
        );
    }

    @Override
    public List<Movie> getMoviesByEmissionDate(String dateFrom, String dateTo) {
        final String SQL = """
                    select m.id,
                    m.title,
                    m.genre,
                    m.start_date,
                    m.end_date
                    from movies m
                    where m.start_date
                    between :dateFrom and :dateTo
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("dateFrom", dateFrom)
                .bind("dateTo", dateTo)
                .mapToBean(Movie.class)
                .list());

    }
    @Override
    public List<Movie> getMoviesByFirstLetters(String firstLetters) {
        final String SQL = """
                    select m.id,
                    m.title,
                    m.genre,
                    m.start_date,
                    m.end_date
                    from movies m
                    where m.title LIKE :letters '%'
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("letters", firstLetters)
                .mapToBean(Movie.class)
                .list()
        );
    }

}
