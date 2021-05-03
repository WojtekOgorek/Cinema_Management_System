package ogorek.wojciech.domain.model.city.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;

import java.util.List;

public class CreateCityDtoListJsonConverter extends JsonConverter<List<CreateCityDto>> {
    public CreateCityDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
