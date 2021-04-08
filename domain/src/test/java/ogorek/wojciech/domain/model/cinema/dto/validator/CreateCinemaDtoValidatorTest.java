package ogorek.wojciech.domain.model.cinema.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;



public class CreateCinemaDtoValidatorTest {


    @Test
    @DisplayName("when create cinema dto is null")
    public void test1(){
        assertThatThrownBy(() -> Validator.validate(new CreateCinemaDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema object: is null");
    }

    @Test
    @DisplayName("when create cinema dto name is invalid")
    public void test2(){

        var name = "multikino";
        var cityId = 2L;

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateCinemaDtoValidator(), createCinemaDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema name is invalid");
    }

    @Test
    @DisplayName("when create cinema dto city id is invalid")
    public void test3(){

        var name = "multikino";
        var cityId = 0L;

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateCinemaDtoValidator(), createCinemaDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema city id is invalid:");

    }

    @Test
    @DisplayName("when create cinema dto has no errors")
    public void test4(){

        var name = "Multikino";
        var cityId = 1L;

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        assertDoesNotThrow(() -> Validator.validate(new CreateCinemaDtoValidator(), createCinemaDto));
    }
}
