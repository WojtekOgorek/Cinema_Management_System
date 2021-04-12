package ogorek.wojciech.domain.model.favourite;

import ogorek.wojciech.domain.model.favourite.dto.GetFavDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class FavouriteDomainTest {

    @Test
    @DisplayName("when conversion to get fav dto is correct")
    public void test1() {

        var id = 3L;
        var userId = 1L;
        var movieId = 5L;
        var addDate = LocalDateTime.now();

        var favourite = Favourite
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

        var expectedFavourite = GetFavDto
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

        var getFavouriteDto = favourite.toGetFavDto();

        assertThat(getFavouriteDto).isEqualTo(expectedFavourite);
    }

    @Test
    @DisplayName("when favourite has all necessary properties")
    public void test2() {

        var id = 3L;
        var userId = 1L;
        var movieId = 5L;
        var addDate = LocalDateTime.now();

        var favourite = Favourite
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

        assertThat(favourite).hasFieldOrProperty("id");
        assertThat(favourite).hasFieldOrProperty("userId");
        assertThat(favourite).hasFieldOrProperty("movieId");
        assertThat(favourite).hasFieldOrProperty("addDate");

    }

    @Test
    @DisplayName("when favourite properties are correct")
    public void test3() {

        var id = 3L;
        var userId = 1L;
        var movieId = 5L;
        var addDate = LocalDateTime.now();

        var favourite = Favourite
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

        Assertions.assertAll(
                "when favourite properties are correct",
                () -> assertThat(favourite.id).isGreaterThan(0).isNotEqualTo(null),
                () -> assertThat(favourite.userId).isGreaterThan(0).isNotEqualTo(null),
                () -> assertThat(favourite.movieId).isGreaterThan(0).isNotEqualTo(null),
                () -> assertThat(favourite.addDate).isInstanceOf(LocalDateTime.class)
        );
    }

    @Test
    @DisplayName("when favourite is not null")
    public void test4(){

        var id = 3L;
        var userId = 1L;
        var movieId = 5L;
        var addDate = LocalDateTime.now();

        var favourite = Favourite
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

        assertThat(favourite).isNotEqualTo(null);
    }
}
