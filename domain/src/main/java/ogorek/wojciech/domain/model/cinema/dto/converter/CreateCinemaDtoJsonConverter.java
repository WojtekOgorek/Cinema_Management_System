package ogorek.wojciech.domain.model.cinema.dto.converter;

import ogorek.wojciech.domain.model.city.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;

import java.util.List;

public class CreateCinemaDtoJsonConverter extends JsonConverter<List<CreateCinemaDto>> {
    public CreateCinemaDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
