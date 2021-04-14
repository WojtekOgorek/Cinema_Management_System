package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularCityDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MostPopularCity {
    String city;
    Integer counter;

    public GetMostPopularCityDto toGetMostPopularCityDto(){
        return GetMostPopularCityDto
                .builder()
                .city(city)
                .counter(counter)
                .build();
    }
}
