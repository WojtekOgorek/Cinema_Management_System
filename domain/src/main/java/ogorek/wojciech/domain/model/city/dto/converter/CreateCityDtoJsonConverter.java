package ogorek.wojciech.domain.model.city.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;

public class CreateCityDtoJsonConverter extends JsonConverter<CreateCityDto> {
    public CreateCityDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
