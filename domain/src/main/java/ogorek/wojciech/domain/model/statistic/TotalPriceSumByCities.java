package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetTotalPriceSumByCitiesDto;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class TotalPriceSumByCities {
    Long cityId;
    BigDecimal price;

    public GetTotalPriceSumByCitiesDto toGetTotalPriceSumByCitiesDto(){
        return GetTotalPriceSumByCitiesDto
                .builder()
                .cityId(cityId)
                .price(price)
                .build();
    }
}
