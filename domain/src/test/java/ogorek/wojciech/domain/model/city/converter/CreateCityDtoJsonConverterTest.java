package ogorek.wojciech.domain.model.city.converter;


import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoJsonConverter;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(CreateCityDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateCityDtoJsonConverterTest {

    private final CreateCityDtoJsonConverter createCityDtoJsonConverter;

    @Test
    @DisplayName("when create city dto json converter works properly")
    public void test1() {
        var expectedCity = List.of(
                CreateCityDto
                        .builder()
                        .name("London")
                        .build()
        );

        var citiesFromJson = createCityDtoJsonConverter.fromJson().orElseThrow();

        Assertions.assertDoesNotThrow(() -> assertThat(citiesFromJson)
                .hasSize(1)
                .containsExactlyElementsOf(expectedCity));
    }


}
