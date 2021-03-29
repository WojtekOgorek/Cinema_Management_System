package ogorek.wojciech.domain.model.city.views;

import lombok.*;
import ogorek.wojciech.domain.model.city.dto.GetCityWithCinemasDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CityWithCinemas {
    Long cityId;
    Long cinemaId;

    public GetCityWithCinemasDto getCityWithCinemasDto(){
        return GetCityWithCinemasDto
                .builder()
                .cityId(cityId)
                .cinemaId(cinemaId)
                .build();
    }
}
