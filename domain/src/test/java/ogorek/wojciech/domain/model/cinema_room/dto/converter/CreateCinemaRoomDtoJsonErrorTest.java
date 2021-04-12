package ogorek.wojciech.domain.model.cinema_room.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.cinema_room.dto.converter.CreateCinemaRoomDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateCinemaRoomDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCinemaRoomDtoJsonErrorTest {

    private final CreateCinemaRoomDtoJsonConverter createCinemaRoomDtoJsonConverter;

    @Test
    @DisplayName("when create cinema room dto converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createCinemaRoomDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
