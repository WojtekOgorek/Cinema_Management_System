package ogorek.wojciech.domain.model.city.dto.converter;

import ogorek.wojciech.domain.model.city.converter.JsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;

import java.util.List;

public class CreateCityDtoJsonConverter extends JsonConverter<List<CreateCityDto>> {
    public CreateCityDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
