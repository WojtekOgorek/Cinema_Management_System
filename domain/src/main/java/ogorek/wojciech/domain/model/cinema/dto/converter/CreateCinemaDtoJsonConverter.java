package ogorek.wojciech.domain.model.cinema.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;

public class CreateCinemaDtoJsonConverter extends JsonConverter<CreateCinemaDto> {
    public CreateCinemaDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
