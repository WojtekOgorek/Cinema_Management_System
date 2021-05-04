package ogorek.wojciech.domain.model.seat.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;

import java.util.List;

public class CreateSeatDtoListJsonConverter extends JsonConverter<List<CreateSeatDto>> {
    public CreateSeatDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
