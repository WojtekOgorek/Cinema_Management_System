package ogorek.wojciech.domain.model.seance;

import ogorek.wojciech.domain.model.seance.dto.GetSeanceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SeanceDomainTest {

    @Test
    @DisplayName("when conversion to get seance dto is correct")
    public void test1() {
        var id = 1L;
        var movieId = 2L;
        var cinemaRoomId = 3L;
        var dateTime = LocalDateTime.of(2021, 1, 1, 12, 15, 15);

        var seance = Seance
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        var expectedSeance = GetSeanceDto
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        var getSeanceDto = seance.toGetSeanceDto();

        assertThat(getSeanceDto).isEqualTo(expectedSeance);
    }

    @Test
    @DisplayName("when seance has all necessary properties")
    public void test2(){
        var id = 1L;
        var movieId = 2L;
        var cinemaRoomId = 3L;
        var dateTime = LocalDateTime.of(2021, 1, 1, 12, 15, 15);

        var seance = Seance
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThat(seance).hasFieldOrProperty("id");
        assertThat(seance).hasFieldOrProperty("movieId");
        assertThat(seance).hasFieldOrProperty("cinemaRoomId");
        assertThat(seance).hasFieldOrProperty("dateTime");

    }

    @Test
    @DisplayName("when seance properties are correct")
    public void test3() {
        var id = 1L;
        var movieId = 2L;
        var cinemaRoomId = 3L;
        var dateTime = LocalDateTime.of(2021, 1, 1, 12, 15, 15);

        var seance = Seance
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThat(seance.id).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seance.movieId).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seance.cinemaRoomId).isGreaterThan(0).isNotEqualTo(null);
        assertThat(seance.dateTime).isInstanceOf(LocalDateTime.class);

    }

    @Test
    @DisplayName("when seance is not null")
    public void test4(){
        var id = 1L;
        var movieId = 2L;
        var cinemaRoomId = 3L;
        var dateTime = LocalDateTime.of(2021, 1, 1, 12, 15, 15);

        var seance = Seance
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThat(seance).isNotEqualTo(null);
    }
}
