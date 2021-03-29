package ogorek.wojciech.domain.model.city.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.city.City;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class CreateCityDto {
    private String name;


    public City toCity(){
        return City
                .builder()
                .name(name)
                .build();
    }
}
