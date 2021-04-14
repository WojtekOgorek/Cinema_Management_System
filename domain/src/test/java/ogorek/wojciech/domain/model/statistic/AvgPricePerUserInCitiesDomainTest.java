package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetAvgPricePerCapitaInCitiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AvgPricePerUserInCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get avg price per user in cities dto is correct")
    public void test1(){

        var cityId = 1L;
        var price = new BigDecimal("100");

        var avgPricePerUserInCities = AvgPricePerUserInCities
                .builder()
                .cityId(cityId)
                .price(price)
                .build();

        var expectedDto = GetAvgPricePerCapitaInCitiesDto
                .builder()
                .cityId(cityId)
                .price(price)
                .build();

        var getAvgPricePerUserInCities = avgPricePerUserInCities.toGetAvgPricePerUserInCitiesDto();

        assertThat(getAvgPricePerUserInCities).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when avg price per user in cities dto has all necessary properties")
    public void test2(){

        var cityId = 1L;
        var price = new BigDecimal("100");

        var avgPricePerUserInCities = AvgPricePerUserInCities
                .builder()
                .cityId(cityId)
                .price(price)
                .build();


        assertThat(avgPricePerUserInCities).hasFieldOrProperty("cityId");
        assertThat(avgPricePerUserInCities).hasFieldOrProperty("price");
    }
}
