package ogorek.wojciech.domain.model.seance.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;

public class CreateSeanceDtoJsonConverter extends JsonConverter<CreateSeanceDto> {
    public CreateSeanceDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
