package ogorek.wojciech.domain.model.seat.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;

import java.util.List;

public class CreateSeatDtoJsonConverter extends JsonConverter<List<CreateSeatDto>> {
    public CreateSeatDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
