package ogorek.wojciech.domain.model.seat.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;

public class CreateSeatDtoJsonConverter extends JsonConverter<CreateSeatDto> {
    public CreateSeatDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
