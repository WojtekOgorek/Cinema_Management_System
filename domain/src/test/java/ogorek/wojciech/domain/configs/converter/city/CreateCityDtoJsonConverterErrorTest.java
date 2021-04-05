package ogorek.wojciech.domain.configs.converter.city;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoJsonConverter;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoJsonErrorExtension;
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

        var abc = createCityDtoJsonConverter.fromJson().orElseThrow();


    }
}
