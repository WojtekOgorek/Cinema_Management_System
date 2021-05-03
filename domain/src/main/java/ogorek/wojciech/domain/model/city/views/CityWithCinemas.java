package ogorek.wojciech.domain.model.city.views;

import lombok.*;
import ogorek.wojciech.domain.model.city.dto.GetCityWithCinemasDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityWithCinemas {
    private Long cityId;
    private Long cinemaId;

    public GetCityWithCinemasDto getCityWithCinemasDto(){
        return GetCityWithCinemasDto
                .builder()
                .cityId(cityId)
                .cinemaId(cinemaId)
                .build();
    }
}
