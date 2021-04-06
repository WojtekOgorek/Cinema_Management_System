package ogorek.wojciech.domain.model.cinema.view;

import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithSeancesDto;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaWithSeancesDomainTest {

    @Test
    @DisplayName("when get cinema with seances dto is correct")
    public void test1(){

        var cinemaId = 1L;
        var seanceId = 3L;

        var cinemaWithSeances = CinemaWithSeances
                .builder()
                .cinemaId(cinemaId)
                .seanceId(seanceId)
                .build();

        var expectedCinemaWithSeances = GetCinemaWithSeancesDto
                .builder()
                .cinemaId(cinemaId)
                .seanceId(seanceId)
                .build();

        var getCinemaWithSeancesDto = cinemaWithSeances.getCinemaWithSeancesDto();

        assertThat(getCinemaWithSeancesDto).isEqualTo(expectedCinemaWithSeances);
    }

    @Test
    @DisplayName("when cinema with seances has correct properties")
    public void test2(){

        var cinemaId = 1L;
        var seanceId = 3L;

        var cinemaWithSeances = CinemaWithSeances
                .builder()
                .cinemaId(cinemaId)
                .seanceId(seanceId)
                .build();

        assertThat(cinemaWithSeances).hasOnlyFields("cinemaId", "seanceId");

    }
}
