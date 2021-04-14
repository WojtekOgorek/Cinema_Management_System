package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetTotalPriceSumByCitiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceSumByCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get total price sum by cities dto is correct")
    public void test1(){

        var cityId = 1L;
        var price = new BigDecimal("100");

        var totalPriceSumByCities = TotalPriceSumByCities
                .builder()
                .cityId(cityId)
                .price(price)
                .build();

        var expectedDto = GetTotalPriceSumByCitiesDto
                .builder()
                .cityId(cityId)
                .price(price)
                .build();

        var getTotalPriceSumByCitiesDto = totalPriceSumByCities.toGetTotalPriceSumByCitiesDto();

        assertThat(getTotalPriceSumByCitiesDto).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when total price sum by cities is correct")
    public void test2(){

        var cityId = 1L;
        var price = new BigDecimal("100");

        var totalPriceSumByCities = TotalPriceSumByCities
                .builder()
                .cityId(cityId)
                .price(price)
                .build();

        assertThat(totalPriceSumByCities).hasFieldOrProperty("cityId");
        assertThat(totalPriceSumByCities).hasFieldOrProperty("price");
    }
}
