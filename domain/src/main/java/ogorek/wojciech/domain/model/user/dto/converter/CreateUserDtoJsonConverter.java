package ogorek.wojciech.domain.model.user.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;

public class CreateUserDtoJsonConverter extends JsonConverter<CreateUserDto> {
    public CreateUserDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
