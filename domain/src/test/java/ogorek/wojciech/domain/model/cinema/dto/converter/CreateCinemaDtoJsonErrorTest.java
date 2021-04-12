package ogorek.wojciech.domain.model.cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.cinema.dto.converter.CreateCinemaDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateCinemaDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoJsonErrorTest {

    private final CreateCinemaDtoJsonConverter createCinemaDtoJsonConverter;

    @Test
    @DisplayName("when create cinema dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createCinemaDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");

    }
}
