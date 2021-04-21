package ogorek.wojciech.domain.model.statistic;

import lombok.*;

import ogorek.wojciech.domain.model.statistic.dto.GetAvgPricePerUserInCitiesDto;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AvgPricePerUserInCities {
    Long cityId;
    BigDecimal price;

    public GetAvgPricePerUserInCitiesDto toGetAvgPricePerUserInCitiesDto(){
        return GetAvgPricePerUserInCitiesDto
                .builder()
                .cityId(cityId)
                .price(price)
                .build();
    }
}
