package ogorek.wojciech.domain.model.movie.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;

public class CreateMovieDtoJsonConverter extends JsonConverter<CreateMovieDto> {
    public CreateMovieDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
