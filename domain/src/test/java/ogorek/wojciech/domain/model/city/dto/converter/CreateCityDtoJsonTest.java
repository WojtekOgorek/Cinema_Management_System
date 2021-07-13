package ogorek.wojciech.domain.model.city.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(CreateCityDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateCityDtoJsonTest {

    private final CreateCityDtoJsonConverter createCityDtoJsonConverter;

    @Test
    @DisplayName("when create city dto json converter works properly")
    public void test1() {
        var expectedCity =
                CreateCityDto
                        .builder()
                        .name("London")
                        .build();

        var citiesFromJson = createCityDtoJsonConverter.fromJson().orElseThrow();


        Assertions.assertDoesNotThrow(() -> assertThat(citiesFromJson)
                .isEqualTo(expectedCity));
    }
}
