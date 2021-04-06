package ogorek.wojciech.domain.model.cinema.view;

import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithMoviesDto;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaWithMoviesDomainTest {

    @Test
    @DisplayName("when get cinema with movies dto is correct")
    public void test1(){

        var cinemaId = 3L;
        var movieId = 1L;
        var seanceId = 5L;

        var cinemaWithMovies = CinemaWithMovies
                .builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .seanceId(seanceId)
                .build();

        var expectedCinemaWithMovies = GetCinemaWithMoviesDto
                .builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .seanceId(seanceId)
                .build();

        var getCinemaWithMoviesDto = cinemaWithMovies.getCinemaWithMoviesDto();

        assertThat(getCinemaWithMoviesDto).isEqualTo(expectedCinemaWithMovies);
    }

    @Test
    @DisplayName("when cinema with movies has correct properties")
    public void test2(){

        var cinemaId = 3L;
        var movieId = 1L;
        var seanceId = 5L;

        var cinemaWithMovies = CinemaWithMovies
                .builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .seanceId(seanceId)
                .build();

        assertThat(cinemaWithMovies).hasFieldOrProperty("cinemaId");
        assertThat(cinemaWithMovies).hasFieldOrProperty("movieId");
    }
}
