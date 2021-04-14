package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularMovieInCitiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MostPopularMovieInCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get most popular movie in cities dto is correct")
    public void test1() {

        var movieId = 1L;
        var cityId = 2L;

        var mostPopularMovieInCities = MostPopularMovieInCities
                .builder()
                .movieId(movieId)
                .cityId(cityId)
                .build();

        var expectedDto = GetMostPopularMovieInCitiesDto
                .builder()
                .movieId(movieId)
                .cityId(cityId)
                .build();

        var getMostPopularMovieInCities = mostPopularMovieInCities.toGetMostPopularMovieInCitiesDto();

        assertThat(getMostPopularMovieInCities).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when most popular movie in cities has all necessary properties")
    public void test2() {

        var movieId = 1L;
        var cityId = 2L;

        var mostPopularMovieInCities = MostPopularMovieInCities
                .builder()
                .movieId(movieId)
                .cityId(cityId)
                .build();


        assertThat(mostPopularMovieInCities).hasFieldOrProperty("movieId");
        assertThat(mostPopularMovieInCities).hasFieldOrProperty("cityId");
    }
}
