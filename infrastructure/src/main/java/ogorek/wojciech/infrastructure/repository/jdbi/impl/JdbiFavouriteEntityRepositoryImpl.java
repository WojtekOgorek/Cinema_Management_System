package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.favourite.Favourite;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.FavouriteEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiFavouriteEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbiFavouriteEntityRepositoryImpl extends AbstractCrudRepository<FavouriteEntity, Long> implements JdbiFavouriteEntityRepository {
    public JdbiFavouriteEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<FavouriteUserGenre> findFavouritesUserGenre(Long id) {
        final String SQL = """
                select
                f.id,
                u.id,
                count(m.genre)
                from favourites f 
                join users u on u.id = f.user_id
                join movies m on m.id = f.movie_id
                group by u.id
                order by count(m.genre) desc
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", id)
                .mapToBean(FavouriteUserGenre.class)
                .list());
    }

    @Override
    public List<Favourite> getUserFavourites(Long userId) {
        final String SQL = """
                select
                f.id,
                f.add_date,
                u.id,
                m.id
                from favourites f 
                join users u on u.id = f.user_id
                join movies m on m.id = f.movie_id
                where u.id = :id
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", userId)
                .mapToBean(Favourite.class)
                .list());
    }
}
