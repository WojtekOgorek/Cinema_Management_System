package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularGenreInCitiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MostPopularGenreInCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get most popular genre in cities dto is correct")
    public void test1(){

        var cityId = 1L;
        var genre = "Genre";

        var mostPopularGenreInCities = MostPopularGenreInCities
                .builder()
                .cityId(cityId)
                .genre(genre)
                .build();

        var expectedDto = GetMostPopularGenreInCitiesDto
                .builder()
                .cityId(cityId)
                .genre(genre)
                .build();

        var getMostPopularGenreInCitiesDto = mostPopularGenreInCities.toGetMostPopularGenreInCitiesDto();

        assertThat(getMostPopularGenreInCitiesDto).isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when most popular genre in cities dto has all necessary properties")
    public void test2(){

        var cityId = 1L;
        var genre = "Genre";

        var mostPopularGenreInCities = MostPopularGenreInCities
                .builder()
                .cityId(cityId)
                .genre(genre)
                .build();


        assertThat(mostPopularGenreInCities).hasFieldOrProperty("cityId");
        assertThat(mostPopularGenreInCities).hasFieldOrProperty("genre");

    }
}
