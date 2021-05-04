package ogorek.wojciech.domain.model.seance.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.seance.dto.converter.CreateSeanceDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateSeanceDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateSeanceDtoListJsonErrorTest {

    private final CreateSeanceDtoListJsonConverter createSeanceDtoListJsonConverter;

    @Test
    @DisplayName("when create seance dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createSeanceDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
