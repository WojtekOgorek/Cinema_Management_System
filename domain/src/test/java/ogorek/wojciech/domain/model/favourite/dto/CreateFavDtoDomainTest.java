package ogorek.wojciech.domain.model.favourite.dto;

import ogorek.wojciech.domain.model.favourite.Favourite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateFavDtoDomainTest {

    @Test
    @DisplayName("when creating favourite is correct")
    public void test1(){

        var userId = 1L;
        var movieId = 5L;

        var createFavouriteDto = CreateFavDto
                .builder()
                .userId(userId)
                .movieId(movieId)
                .build();

        var favourite = createFavouriteDto.toFavourite();

        var expectedFavourite = Favourite
                .builder()
                .userId(userId)
                .movieId(movieId)
                .addDate(favourite.toGetFavDto().getAddDate())
                .build();


        assertThat(favourite).isEqualTo(expectedFavourite);
    }

    @Test
    @DisplayName("when create favourite dto has correct properties")
    public void test2(){

        var userId = 1L;
        var movieId = 5L;

        var createFavouriteDto = CreateFavDto
                .builder()
                .userId(userId)
                .movieId(movieId)
                .build();

        assertThat(createFavouriteDto).hasOnlyFields("userId", "movieId");
    }
}
