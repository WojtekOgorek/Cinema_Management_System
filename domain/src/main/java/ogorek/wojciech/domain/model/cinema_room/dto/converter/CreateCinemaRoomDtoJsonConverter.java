package ogorek.wojciech.domain.model.cinema_room.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

public class CreateCinemaRoomDtoJsonConverter extends JsonConverter<List<CreateCinemaRoomDto>> {
    public CreateCinemaRoomDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
