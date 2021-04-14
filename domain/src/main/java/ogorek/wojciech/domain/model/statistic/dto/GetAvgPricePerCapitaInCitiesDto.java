package ogorek.wojciech.domain.model.statistic.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetAvgPricePerCapitaInCitiesDto {
    private Long cityId;
    private BigDecimal price;
}
