package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.city.City;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityEntity {
    private Long id;
    private String name;

    public City toCity() {
        return City
                .builder()
                .id(id)
                .name(name)
                .build();
    }

    public CityEntity fromCity(City city){
        var getCityDto = city.toGetCityDto();
        return CityEntity
                .builder()
                .id(getCityDto.getId())
                .name(getCityDto.getName())
                .build();
    }
}
