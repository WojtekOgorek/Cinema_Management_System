package ogorek.wojciech.domain.model.seat.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import ogorek.wojciech.extension.seat.dto.converter.CreateSeatDtoListJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateSeatDtoListJsonExtension.class)
@RequiredArgsConstructor
public class CreateSeatDtoListJsonTest {

    private final CreateSeatDtoListJsonConverter createSeatDtoListJsonConverter;

    @Test
    @DisplayName("when create seat dto list json converter work properly")
    public void test1(){
        var seatRow = 2;
        var seatPlace = 5;
        var cinemaRoomId = 10L;


        var createSeatDto = List.of(CreateSeatDto
                .builder()
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build());


        var seatFromJson = createSeatDtoListJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(seatFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(createSeatDto);
    }
}
