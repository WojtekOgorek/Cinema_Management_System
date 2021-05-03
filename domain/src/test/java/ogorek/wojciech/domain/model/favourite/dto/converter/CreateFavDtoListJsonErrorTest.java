package ogorek.wojciech.domain.model.favourite.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.favourite.dto.converter.CreateFavDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateFavDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateFavDtoListJsonErrorTest {

    private final CreateFavouriteDtoListJsonConverter createFavouriteDtoListJsonConverter;

    @Test
    @DisplayName("when create favourite dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createFavouriteDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
