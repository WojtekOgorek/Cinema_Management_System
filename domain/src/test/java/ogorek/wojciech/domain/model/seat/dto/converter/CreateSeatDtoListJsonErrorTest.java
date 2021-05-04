package ogorek.wojciech.domain.model.seat.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.seat.dto.converter.CreateSeatDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateSeatDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateSeatDtoListJsonErrorTest {

    private final CreateSeatDtoListJsonConverter createSeatDtoListJsonConverter;

    @Test
    @DisplayName("when create seat dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createSeatDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
