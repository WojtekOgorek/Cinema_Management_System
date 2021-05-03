package ogorek.wojciech.domain.model.cinema.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;

import java.util.List;

public class CreateCinemaDtoListJsonConverter extends JsonConverter<List<CreateCinemaDto>> {
    public CreateCinemaDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
