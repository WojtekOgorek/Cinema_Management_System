package ogorek.wojciech.domain.model.cinema_room.view;

import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeanceDto;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaRoomWithSeanceDomainTest {

    @Test
    @DisplayName("when conversion to get cinema room with seance is correct")
    public void test1(){

        var cinemaRoomId = 1L;
        var seanceId = 3L;

        var cinemaRoomWithSeance = CinemaRoomWithSeance
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seanceId(seanceId)
                .build();

        var expectedCinemaRoomWithSeance = GetCinemaRoomWithSeanceDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seanceId(seanceId)
                .build();

        var getCinemaRoomWithSeanceDto = cinemaRoomWithSeance.toGetCinemaRoomWithSeanceDto();

        assertThat(getCinemaRoomWithSeanceDto).isEqualTo(expectedCinemaRoomWithSeance);
    }

    @Test
    @DisplayName("when cinema rooms with seance has correct properties")
    public void test2(){

        var cinemaRoomId = 3L;
        var seanceId = 4L;

        var cinemaRoomWithSeance = CinemaRoomWithSeance
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seanceId(seanceId)
                .build();

        assertThat(cinemaRoomWithSeance).hasOnlyFields("cinemaRoomId", "seanceId");
    }
}
