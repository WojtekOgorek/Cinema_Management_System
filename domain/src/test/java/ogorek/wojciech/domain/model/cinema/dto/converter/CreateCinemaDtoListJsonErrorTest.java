package ogorek.wojciech.domain.model.cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.cinema.dto.converter.CreateCinemaDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateCinemaDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoListJsonErrorTest {

    private final CreateCinemaDtoListJsonConverter createCinemaDtoListJsonConverter;

    @Test
    @DisplayName("when create cinema dto list json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createCinemaDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");

    }
}
