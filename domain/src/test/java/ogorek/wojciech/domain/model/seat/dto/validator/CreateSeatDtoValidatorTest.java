package ogorek.wojciech.domain.model.seat.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateSeatDtoValidatorTest {

    @Test
    @DisplayName("when create seat dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateSeatDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Seat object is invalid:");
    }

    @Test
    @DisplayName("when create seat dto cinema id is invalid")
    public void test2(){

        var cinemaRoomId = 0L;
        var seatRow = 2;
        var seatPlace = 3;

        var createSeatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeatDtoValidator(), createSeatDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Seat cinema room id is invalid:");
    }

    @Test
    @DisplayName("when create seat dto seat place is invalid")
    public void test3(){

        var cinemaRoomId = 1L;
        var seatRow = 2;
        var seatPlace = 0;

        var createSeatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeatDtoValidator(), createSeatDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Seat place is invalid:");
    }

    @Test
    @DisplayName("when create seat dto seat row is invalid")
    public void test4(){

        var cinemaRoomId = 1L;
        var seatRow = 2;
        var seatPlace = 0;

        var createSeatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeatDtoValidator(), createSeatDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Seat row is invalid:");
    }

}
