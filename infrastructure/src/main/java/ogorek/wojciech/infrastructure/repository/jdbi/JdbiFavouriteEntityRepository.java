package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.favourite.Favourite;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;
import ogorek.wojciech.infrastructure.repository.entity.FavouriteEntity;

import java.util.List;

public interface JdbiFavouriteEntityRepository extends CrudRepository<FavouriteEntity, Long> {

    List<FavouriteUserGenre> findFavouritesUserGenre(Long id);
    List<FavouriteEntity> getUserFavourites(Long userId);

}
