package ogorek.wojciech.domain.model.seance.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.seance.dto.converter.CreateSeanceDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateSeanceDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateSeanceDtoJsonErrorTest {

    private final CreateSeanceDtoJsonConverter createSeanceDtoJsonConverter;

    @Test
    @DisplayName("when create seance dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createSeanceDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
