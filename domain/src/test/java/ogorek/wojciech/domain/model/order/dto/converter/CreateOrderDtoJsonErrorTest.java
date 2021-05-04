package ogorek.wojciech.domain.model.order.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.order.dto.converter.CreateOrderDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateOrderDtoListJsonErrorExtension.class)
@RequiredArgsConstructor

public class CreateOrderDtoJsonErrorTest {

    private final CreateOrderDtoListJsonConverter createOrderDtoListJsonConverter;

    @Test
    @DisplayName("when create order dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createOrderDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
