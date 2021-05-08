package ogorek.wojciech.domain.model.seat.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import ogorek.wojciech.extension.seat.dto.converter.CreateSeatDtoJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateSeatDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreatSeatDtoJsonTest {

    private final CreateSeatDtoJsonConverter createSeatDtoJsonConverter;

    @Test
    @DisplayName("when create seat dto json converter work properly")
    public void test1(){
        var seatRow = 2;
        var seatPlace = 5;
        var cinemaRoomId = 10L;


        var createSeatDto = CreateSeatDto
                .builder()
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();


        var seatFromJson = createSeatDtoJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(seatFromJson))
                .isEqualTo(createSeatDto);
    }
}
