package ogorek.wojciech.domain.model.cinema_room.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.extension.cinema_room.dto.converter.CreateCinemaRoomDtoListJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(CreateCinemaRoomDtoListJsonExtension.class)
@RequiredArgsConstructor
public class CreateCinemaRoomDtoListJsonTest {

    private final CreateCinemaRoomDtoListJsonConverter createCinemaRoomDtoListJsonConverter;

    @Test
    @DisplayName("when create cinema room list json converter work properly")
    public void test1(){

        var expectedCinemaRoom = List.of(CreateCinemaRoomDto
                .builder()
                .name("Room 1")
                .rowQuantity(5)
                .placeQuantity(10)
                .cinemaId(2L)
                .build());

        var cinemaRoomFromJson = createCinemaRoomDtoListJsonConverter.fromJson().orElseThrow();

        Assertions.assertDoesNotThrow(() -> assertThat(cinemaRoomFromJson)
                .hasSize(1)
                .containsExactlyElementsOf(expectedCinemaRoom));
    }
}
