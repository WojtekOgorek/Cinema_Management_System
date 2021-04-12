package ogorek.wojciech.domain.model.seance.dto;

import ogorek.wojciech.domain.model.seance.Seance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateSeanceDtoDomainTest {

    @Test
    @DisplayName("when creating seance is correct")
    public void test1(){
        var movieId = 1L;
        var cinemaRoomId = 2L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15,15);

        var createSeanceDto = CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        var expectedSeance = Seance
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        var seance = createSeanceDto.toSeance();

        assertThat(seance).isEqualTo(expectedSeance);
    }

    @Test
    @DisplayName("when create seance dto has correct proprties")
    public void test2(){

        var movieId = 1L;
        var cinemaRoomId = 2L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15,15);

        var createSeanceDto = CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThat(createSeanceDto).hasOnlyFields("movieId", "cinemaRoomId", "dateTime");
    }
}
