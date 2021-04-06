package ogorek.wojciech.domain.model.cinema.view;

import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithCinemaRoomsDto;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaWithCinemaRoomsDomainTest {

    @Test
    @DisplayName("when conversion to get cinema with cinema rooms dto is correct")
    public void test1(){

        var cinemaId = 3L;
        var cinemaRoomId = 4L;

        var cinemaWithCinemaRooms = CinemaWithCinemaRooms
                .builder()
                .cinemaId(cinemaId)
                .cinemaRoomId(cinemaRoomId)
                .build();

        var expectedCinemaWithCinemaRooms = GetCinemaWithCinemaRoomsDto
                .builder()
                .cinemaId(cinemaId)
                .cinemaRoomId(cinemaRoomId)
                .build();

        var getCinemaWithCinemaRoomsDto = cinemaWithCinemaRooms.toGetCinemaWithCinemaRoomsDto();

        assertThat(getCinemaWithCinemaRoomsDto).isEqualTo(expectedCinemaWithCinemaRooms);
    }

    @Test
    @DisplayName("when cinema with cinema rooms has correct properties")
    public void test2(){

        var cinemaId = 3L;
        var cinemaRoomId = 4L;

        var cinemaWithCinemaRooms = CinemaWithCinemaRooms
                .builder()
                .cinemaId(cinemaId)
                .cinemaRoomId(cinemaRoomId)
                .build();

        assertThat(cinemaWithCinemaRooms).hasOnlyFields("cinemaId", "cinemaRoomId");
    }
}
