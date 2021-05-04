package ogorek.wojciech.domain.model.seance.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;

import java.util.List;

public class CreateSeanceDtoListJsonConverter extends JsonConverter<List<CreateSeanceDto>> {
    public CreateSeanceDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
