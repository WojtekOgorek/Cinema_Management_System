package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularCityDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularCity {
    private String city;
    private Integer counter;

    public GetMostPopularCityDto toGetMostPopularCityDto(){
        return GetMostPopularCityDto
                .builder()
                .city(city)
                .counter(counter)
                .build();
    }
}
