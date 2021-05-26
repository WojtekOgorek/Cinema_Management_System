package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.favourite.Favourite;
import ogorek.wojciech.domain.model.favourite.repository.FavouritesRepository;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;
import ogorek.wojciech.infrastructure.repository.entity.FavouriteEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiFavouriteEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FavouriteRepositoryImpl implements FavouritesRepository {

    private final JdbiFavouriteEntityRepository jdbiFavouriteEntityRepository;


    @Override
    public List<Favourite> findAll() {
        return jdbiFavouriteEntityRepository
                .findAll()
                .stream()
                .map(FavouriteEntity::toFavourite)
                .collect(Collectors.toList());
    }

    @Override
    public List<Favourite> findAllById(List<Long> ids) {
        return jdbiFavouriteEntityRepository
                .findAllById(ids)
                .stream()
                .map(FavouriteEntity::toFavourite)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Favourite> findById(Long id) {
        return jdbiFavouriteEntityRepository
                .findById(id)
                .map(FavouriteEntity::toFavourite);
    }

    @Override
    public Optional<Favourite> findLast() {
        return jdbiFavouriteEntityRepository
                .findLast()
                .map(FavouriteEntity::toFavourite);
    }

    @Override
    public Optional<Favourite> add(Favourite favourite) {
        return jdbiFavouriteEntityRepository
                .add(new FavouriteEntity().fromFavourite(favourite))
                .map(FavouriteEntity::toFavourite);
    }

    @Override
    public Optional<Favourite> update(Favourite favourite) {
        return jdbiFavouriteEntityRepository
                .add(new FavouriteEntity().fromFavourite(favourite))
                .map(FavouriteEntity::toFavourite);
    }

    @Override
    public Optional<Favourite> delete(Long id) {
        return jdbiFavouriteEntityRepository
                .delete(id)
                .map(FavouriteEntity::toFavourite);
    }

    @Override
    public boolean deleteAll() {
        return jdbiFavouriteEntityRepository
                .deleteAll();
    }

    @Override
    public List<FavouriteUserGenre> findFavouritesUserGenre(Long id) {
        return jdbiFavouriteEntityRepository
                .findFavouritesUserGenre(id);
    }

    @Override
    public List<Favourite> getUserFavourites(Long userId) {
        return jdbiFavouriteEntityRepository
                .getUserFavourites(userId)
                .stream()
                .map(FavouriteEntity::toFavourite)
                .collect(Collectors.toList());
    }
}
