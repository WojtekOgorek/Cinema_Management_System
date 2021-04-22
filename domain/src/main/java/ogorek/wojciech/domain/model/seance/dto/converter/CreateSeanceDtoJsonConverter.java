package ogorek.wojciech.domain.model.seance.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;

import java.util.List;

public class CreateSeanceDtoJsonConverter extends JsonConverter<List<CreateSeanceDto>> {
    public CreateSeanceDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
