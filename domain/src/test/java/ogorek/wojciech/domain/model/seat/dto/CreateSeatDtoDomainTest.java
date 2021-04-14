package ogorek.wojciech.domain.model.seat.dto;

import ogorek.wojciech.domain.model.seat.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSeatDtoDomainTest {

    @Test
    @DisplayName("when creating seat is correct")
    public void test1(){
        var cinemaRoomId = 1L;
        var seatRow = 2;
        var seatPlace = 3;

        var createSeatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        var expectedSeat = Seat
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        var seat = createSeatDto.toSeat();

        assertThat(expectedSeat).isEqualTo(seat);
    }

    @Test
    @DisplayName("when create seat dto has all necessary properties")
    public void test2(){

        var cinemaRoomId = 1L;
        var seatRow = 2;
        var seatPlace = 3;

        var createSeatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatPlace(seatPlace)
                .seatRow(seatRow)
                .build();

        assertThat(createSeatDto).hasFieldOrProperty("cinemaRoomId");
        assertThat(createSeatDto).hasFieldOrProperty("seatRow");
        assertThat(createSeatDto).hasFieldOrProperty("seatPlace");
    }
}
