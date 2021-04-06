package ogorek.wojciech.domain.model.cinema_room.dto.validator;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.extension.cinema_room.dto.validator.CreateCinemaRoomDtoValidatorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateCinemaRoomDtoValidatorExtension.class)
@RequiredArgsConstructor
public class CreateCinemaRoomDtoValidatorTest {
    private final CreateCinemaRoomDtoValidator createCinemaRoomDtoValidator;

    @Test
    @DisplayName("when create cinema room dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(createCinemaRoomDtoValidator, null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema room object: is null");
    }

    @Test
    @DisplayName("when create cinema room dto name is invalid")
    public void test2(){

        var name = "";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();


        assertThatThrownBy(() -> Validator.validate(createCinemaRoomDtoValidator, createCinemaRoomDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema room name is invalid:");
    }

    @Test
    @DisplayName("when create cinema room dto row quantity is invalid")
    public void test3(){

        var name = "First";
        var rowQuantity = 0;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();


        assertThatThrownBy(() -> Validator.validate(createCinemaRoomDtoValidator, createCinemaRoomDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema room row number is invalid:");
    }

    @Test
    @DisplayName("when create cinema room dto place quantity is invalid")
    public void test4(){

        var name = "First";
        var rowQuantity = 1;
        var placeQuantity = 0;
        var cinemaId = 3L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();


        assertThatThrownBy(() -> Validator.validate(createCinemaRoomDtoValidator, createCinemaRoomDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema room place number is invalid:");
    }

    @Test
    @DisplayName("when create cinema room dto cinema id is invalid")
    public void test5(){

        var name = "First";
        var rowQuantity = 3;
        var placeQuantity = 6;
        var cinemaId = 0L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();


        assertThatThrownBy(() -> Validator.validate(createCinemaRoomDtoValidator, createCinemaRoomDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Cinema room cinema id is invalid:");
    }

    @Test
    @DisplayName("when create cinema room dto has no errors")
    public void test6(){

        var name = "First";
        var rowQuantity = 3;
        var placeQuantity = 6;
        var cinemaId = 5L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();


        assertDoesNotThrow(() -> Validator.validate(createCinemaRoomDtoValidator, createCinemaRoomDto));

    }




}
