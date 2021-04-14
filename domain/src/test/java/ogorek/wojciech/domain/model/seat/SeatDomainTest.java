package ogorek.wojciech.domain.model.seat;

import ogorek.wojciech.domain.model.seat.dto.GetSeatDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeatDomainTest {

    @Test
    @DisplayName("when conversion to get seat dto is correct")
    public void test1(){

        var id = 1L;
        var seatRow = 3;
        var seatPlace = 10;
        var cinemaRoomId = 2L;

        var seat = Seat
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();

        var expectedSeat = GetSeatDto
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();

        var getSeatDto = seat.toGetSeatDto();

        assertThat(getSeatDto).isEqualTo(expectedSeat);
    }

    @Test
    @DisplayName("when seat has all necessary properties")
    public void test2(){

        var id = 1L;
        var seatRow = 3;
        var seatPlace = 10;
        var cinemaRoomId = 2L;

        var seat = Seat
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();


        Assertions.assertAll(
                "when seat has all necessary properties",
                () -> assertThat(seat).hasFieldOrProperty("id"),
                () -> assertThat(seat).hasFieldOrProperty("seatRow"),
                () -> assertThat(seat).hasFieldOrProperty("seatPlace"),
                () -> assertThat(seat).hasFieldOrProperty("cinemaRoomId")
        );
    }

    @Test
    @DisplayName("when seat properties are correct")
    public void test3(){

        var id = 1L;
        var seatRow = 3;
        var seatPlace = 10;
        var cinemaRoomId = 2L;

        var seat = Seat
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();

        assertThat(seat.id).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seat.seatRow).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seat.seatPlace).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seat.cinemaRoomId).isGreaterThan(0).isNotEqualTo(null);
    }

    @Test
    @DisplayName("when seat is not null")
    public void test4(){

        var id = 1L;
        var seatRow = 3;
        var seatPlace = 10;
        var cinemaRoomId = 2L;

        var seat = Seat
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();

        assertThat(seat).isNotEqualTo(null);
    }
}
