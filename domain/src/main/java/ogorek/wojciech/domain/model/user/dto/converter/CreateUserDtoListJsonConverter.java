package ogorek.wojciech.domain.model.user.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;

import java.util.List;

public class CreateUserDtoListJsonConverter extends JsonConverter<List<CreateUserDto>> {
    public CreateUserDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
