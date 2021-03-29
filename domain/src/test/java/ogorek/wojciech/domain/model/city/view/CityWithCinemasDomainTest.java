package ogorek.wojciech.domain.model.city.view;

import ogorek.wojciech.domain.model.city.dto.GetCityWithCinemasDto;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CityWithCinemasDomainTest {

    @Test
    @DisplayName("when creating getCityWithCinemasDto is correct")
    public void test1(){

        var cityId = 1L;
        var cinemaId = 4L;

        var cityWithCinemas = CityWithCinemas
                .builder()
                .cityId(cityId)
                .cinemaId(cinemaId)
                .build();

        var expectedCityWithCinemas = GetCityWithCinemasDto
        .builder()
        .cityId(cityId)
        .cinemaId(cinemaId)
        .build();

        var getCityWithCinemasDto = cityWithCinemas.getCityWithCinemasDto();

        assertThat(getCityWithCinemasDto).isEqualTo(expectedCityWithCinemas);
    }

    @Test
    @DisplayName("when CityWithCinemas has correct properties")
    public void test2(){

        var cityId = 3L;
        var cinemaId = 5L;

        var cityWithCinemas = CityWithCinemas
                .builder()
                .cityId(cityId)
                .cinemaId(cinemaId)
                .build();

        assertThat(cityWithCinemas).hasFieldOrProperty("cityId");
        assertThat(cityWithCinemas).hasFieldOrProperty("cinemaId");
    }
}
