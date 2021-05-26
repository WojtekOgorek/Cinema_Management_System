package ogorek.wojciech.domain.model.cinema_room.view;

import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeatsDto;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaRoomWithSeatsDomainTest {

    @Test
    @DisplayName("when conversion to get cinema room with seats is correct")
    public void test1(){

        var cinemaRoomId = 1L;
        var seatId = 3L;

        var cinemaRoomWithSeats = CinemaRoomWithSeats
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatId(seatId)
                .build();

        var expectedCinemaRoomWithSeats = GetCinemaRoomWithSeatsDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatId(seatId)
                .build();

        var getCinemaRoomWithSeatsDto = cinemaRoomWithSeats.toCinemaRoomWithSeatsDto();

        assertThat(getCinemaRoomWithSeatsDto).isEqualTo(expectedCinemaRoomWithSeats);
    }

    @Test
    @DisplayName("when create cinema room with seats has correct properties")
    public void test2(){

        var cinemaRoomId = 1L;
        var seatId = 3L;

        var cinemaRoomWithSeats = CinemaRoomWithSeats
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatId(seatId)
                .build();

        assertThat(cinemaRoomWithSeats).hasFieldOrProperty("cinemaRoomId");
        assertThat(cinemaRoomWithSeats).hasFieldOrProperty("seatId");

    }
}
