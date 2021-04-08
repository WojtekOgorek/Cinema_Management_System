package ogorek.wojciech.domain.model.city.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CreateCityDtoValidatorTest {



    @Test
    @DisplayName("when create city dto is null")
    public void test1(){
        assertThatThrownBy(() -> Validator.validate(new CreateCityDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("City object: is null");
    }

    @Test
    @DisplayName("when create city dto name is invalid")
    public void test2(){
        var createCityDto = CreateCityDto
                .builder()
                .name("asdf")
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateCityDtoValidator(), createCityDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("City name is invalid: It must begin with uppercase");
    }

    @Test
    @DisplayName("when create city dto has no errors")
    public void test3(){
        var createCityDto = CreateCityDto
                .builder()
                .name("Paris")
                .build();

        Assertions.assertDoesNotThrow(() -> Validator.validate(new CreateCityDtoValidator(), createCityDto));
    }
}
