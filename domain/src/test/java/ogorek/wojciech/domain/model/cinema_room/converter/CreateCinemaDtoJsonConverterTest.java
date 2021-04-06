package ogorek.wojciech.domain.model.cinema_room.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.converter.CreateCinemaDtoJsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.domain.model.cinema_room.dto.converter.CreateCinemaRoomDtoJsonConverter;
import ogorek.wojciech.extension.cinema_room.dto.converter.CreateCinemaRoomDtoConverterExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(CreateCinemaRoomDtoConverterExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoJsonConverterTest {

    private final CreateCinemaRoomDtoJsonConverter createCinemaRoomDtoJsonConverter;

    @Test
    @DisplayName("when create cinema room json converter work properly")
    public void test1(){

        var expectedCinemaRoom = List.of(CreateCinemaRoomDto
                .builder()
                .name("Room 1")
                .rowQuantity(5)
                .placeQuantity(10)
                .cinemaId(2L)
                .build());

        var cinemaRoomFromJson = createCinemaRoomDtoJsonConverter.fromJson().orElseThrow();

        assertThat(cinemaRoomFromJson).isEqualTo(expectedCinemaRoom);
    }
}
