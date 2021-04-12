package ogorek.wojciech.domain.model.favourite.view;

import ogorek.wojciech.domain.model.favourite.dto.GetFavUserGenreDto;
import ogorek.wojciech.domain.model.favourite.views.FavouriteUserGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FavouriteUserGenreDomainTest {

    @Test
    @DisplayName("when conversion to get favourite user genre is correct")
    public void test1(){

        var id = 1L;
        var userId = 2L;
        var genre = "Genre";

        var favouriteUserGenre = FavouriteUserGenre
                .builder()
                .id(id)
                .userId(userId)
                .genre(genre)
                .build();

        var expectedFavouriteUserGenre = GetFavUserGenreDto
                .builder()
                .id(id)
                .userId(userId)
                .genre(genre)
                .build();

        var getFavUserGenre = favouriteUserGenre.toGetFavUserGenreDto();

        assertThat(getFavUserGenre).isEqualTo(expectedFavouriteUserGenre);
    }

    @Test
    @DisplayName("when favourite user genre has correct properties")
    public void test2() {
        var id = 1L;
        var userId = 2L;
        var genre = "Genre";

        var favouriteUserGenre = FavouriteUserGenre
                .builder()
                .id(id)
                .userId(userId)
                .genre(genre)
                .build();

        assertThat(favouriteUserGenre).hasOnlyFields("id", "userId", "genre");

    }

}
