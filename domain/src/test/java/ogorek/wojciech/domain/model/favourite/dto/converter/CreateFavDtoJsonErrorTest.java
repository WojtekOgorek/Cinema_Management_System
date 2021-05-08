package ogorek.wojciech.domain.model.favourite.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.favourite.dto.converter.CreateFavDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateFavDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateFavDtoJsonErrorTest {

    private final CreateFavouriteDtoJsonConverter createFavouriteDtoJsonConverter;

    @Test
    @DisplayName("when create favourite dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createFavouriteDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }
}
