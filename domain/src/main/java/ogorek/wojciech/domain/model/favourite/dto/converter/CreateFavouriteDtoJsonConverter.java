package ogorek.wojciech.domain.model.favourite.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;

public class CreateFavouriteDtoJsonConverter extends JsonConverter<CreateFavDto> {
    public CreateFavouriteDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
