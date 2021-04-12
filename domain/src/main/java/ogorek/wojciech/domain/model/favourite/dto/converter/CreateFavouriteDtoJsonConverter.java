package ogorek.wojciech.domain.model.favourite.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;

import java.util.List;

public class CreateFavouriteDtoJsonConverter extends JsonConverter<List<CreateFavDto>> {
    public CreateFavouriteDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
