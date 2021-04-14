package ogorek.wojciech.domain.model.statistic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularCityDto {
    private String city;
    private Integer counter;
}
