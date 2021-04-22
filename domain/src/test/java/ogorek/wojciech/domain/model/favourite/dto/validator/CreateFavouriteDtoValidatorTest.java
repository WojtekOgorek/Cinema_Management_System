package ogorek.wojciech.domain.model.favourite.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateFavouriteDtoValidatorTest {

    @Test
    @DisplayName("when create favourite dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateFavouriteDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Favourite object is invalid:");
    }

    @Test
    @DisplayName("when create favourite dto user id is invalid")
    public void test2(){

        var userId = 0L;
        var movieId = 5L;

        var createFavDto = CreateFavDto
                .builder()
                .userId(userId)
                .movieId(movieId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateFavouriteDtoValidator(), createFavDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Invalid id number for user:");
    }

    @Test
    @DisplayName("when create favourite dto movie id is invalid")
    public void test3(){

        var userId = 1L;
        var movieId = 0L;

        var createFavDto = CreateFavDto
                .builder()
                .userId(userId)
                .movieId(movieId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateFavouriteDtoValidator(), createFavDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Invalid id number for movie:");
    }
}
