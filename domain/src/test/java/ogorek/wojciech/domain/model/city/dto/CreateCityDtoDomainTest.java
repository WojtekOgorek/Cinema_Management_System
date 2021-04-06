package ogorek.wojciech.domain.model.city.dto;

import ogorek.wojciech.domain.model.city.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateCityDtoDomainTest {

    @Test
    @DisplayName("when creating city is correct")
    public void test1() {

        var name = "City Name";

        var createCityDto = CreateCityDto
                .builder()
                .name(name)
                .build();

        var expectedCity = City
                .builder()
                .name(name)
                .build();

        var city = createCityDto.toCity();

        assertThat(city).isEqualTo(expectedCity);
    }

    @Test
    @DisplayName("when createCityDto has correct properties")
    public void test2(){

        var name = "City Name";

        var createCityDto = CreateCityDto
                .builder()
                .name(name)
                .build();

        assertThat(createCityDto).hasOnlyFields("name");
    }
}
