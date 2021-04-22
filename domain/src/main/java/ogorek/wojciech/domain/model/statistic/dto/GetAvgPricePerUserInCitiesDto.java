package ogorek.wojciech.domain.model.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetAvgPricePerUserInCitiesDto {
    private Long cityId;
    private BigDecimal price;
}
