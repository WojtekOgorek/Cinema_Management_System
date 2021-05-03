package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.movie.Movie;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.MovieEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiMovieEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbiMovieEntityRepositoryImpl extends AbstractCrudRepository<MovieEntity, Long> implements JdbiMovieEntityRepository {
    public JdbiMovieEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<MovieEntity> getMoviesByGenre(String genre) {
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
                .mapToBean(MovieEntity.class)
                .list()
        );
    }

    @Override
    public Optional<MovieEntity> getMovieByName(String movieTitle) {
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
                .mapToBean(MovieEntity.class)
                .findFirst()
        );
    }

    @Override
    public List<MovieEntity> getMoviesByEmissionDate(String dateFrom, String dateTo) {
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
                .mapToBean(MovieEntity.class)
                .list());

    }
    @Override
    public List<MovieEntity> getMoviesByFirstLetters(String firstLetters) {
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
                .mapToBean(MovieEntity.class)
                .list()
        );
    }
}
