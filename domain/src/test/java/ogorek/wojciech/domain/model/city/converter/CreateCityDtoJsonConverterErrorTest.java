package ogorek.wojciech.domain.model.city.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoJsonConverter;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoJsonErrorExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateCityDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCityDtoJsonConverterErrorTest {
    private final CreateCityDtoJsonConverter createCityDtoJsonConverter;

    @Test
    @DisplayName("when json converter works with bad data type")
    public void test1(){

        var expectedCities = createCityDtoJsonConverter.fromJson().orElseThrow();

//       assertThatThrownBy(() -> createCityDtoJsonConverter.fromJson().orElseThrow())
//               .isInstanceOf(AppConverterException.class)
//               .hasMessageContaining("Invalid");


    }
}
