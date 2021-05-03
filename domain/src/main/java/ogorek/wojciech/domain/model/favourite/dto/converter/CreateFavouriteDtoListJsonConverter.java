package ogorek.wojciech.domain.model.favourite.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;

import java.util.List;

public class CreateFavouriteDtoListJsonConverter extends JsonConverter<List<CreateFavDto>> {
    public CreateFavouriteDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
