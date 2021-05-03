package ogorek.wojciech.domain.model.movie.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;

import java.util.List;

public class CreateMovieDtoListJsonConverter extends JsonConverter<List<CreateMovieDto>> {
    public CreateMovieDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
