package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetAvgPricePerCapitaInCitiesDto;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AvgPricePerUserInCities {
    Long cityId;
    BigDecimal price;

    public GetAvgPricePerCapitaInCitiesDto toGetAvgPricePerUserInCitiesDto(){
        return GetAvgPricePerCapitaInCitiesDto
                .builder()
                .cityId(cityId)
                .price(price)
                .build();
    }
}
