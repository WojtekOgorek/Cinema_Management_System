package ogorek.wojciech.domain.model.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularCityDto {
    private String city;
    private Integer counter;
}
