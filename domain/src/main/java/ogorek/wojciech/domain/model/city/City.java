package ogorek.wojciech.domain.model.city;

import lombok.*;
import ogorek.wojciech.domain.model.city.dto.GetCityDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class City {
    Long id;
    String name;

    public GetCityDto toGetCityDto(){
        return GetCityDto
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}
