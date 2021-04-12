package ogorek.wojciech.domain.model.favourite.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.favourite.Favourite;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;

import java.util.List;

public interface FavouritesRepository extends CrudRepository<Favourite, Long> {
    List<FavouriteUserGenre> findFavouritesUserGenre(Long id);
    List<Favourite> getUserFavourites(Long userId);
}

