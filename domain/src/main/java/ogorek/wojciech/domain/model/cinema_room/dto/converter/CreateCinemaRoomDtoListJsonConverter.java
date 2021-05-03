package ogorek.wojciech.domain.model.cinema_room.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

public class CreateCinemaRoomDtoListJsonConverter extends JsonConverter<List<CreateCinemaRoomDto>> {
    public CreateCinemaRoomDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
