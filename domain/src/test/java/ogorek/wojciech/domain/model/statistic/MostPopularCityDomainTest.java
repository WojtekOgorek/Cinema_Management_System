package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularCityDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MostPopularCityDomainTest {

    @Test
    @DisplayName("when conversion to get most popular city dto is correct")
    public void test1() {
        var city = "City";
        var counter = 5;

        var mostPopularCity = MostPopularCity
                .builder()
                .city(city)
                .counter(counter)
                .build();

        var expectedDto = GetMostPopularCityDto
                .builder()
                .city(city)
                .counter(counter)
                .build();

        var getMostPopularCityDto = mostPopularCity.toGetMostPopularCityDto();

        assertThat(getMostPopularCityDto).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when most popular city has all necessary properties")
    public void test2() {
        var city = "City";
        var counter = 5;

        var mostPopularCity = MostPopularCity
                .builder()
                .city(city)
                .counter(counter)
                .build();

        assertThat(mostPopularCity).hasFieldOrProperty("city");
        assertThat(mostPopularCity).hasFieldOrProperty("counter");
    }

}
