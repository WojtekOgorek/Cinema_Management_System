package ogorek.wojciech.domain.model.seat.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.seat.dto.converter.CreateSeatDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateSeatDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateSeatDtoJsonErrorTest {

    private final CreateSeatDtoJsonConverter createSeatDtoJsonConverter;

    @Test
    @DisplayName("when create seat dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createSeatDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
