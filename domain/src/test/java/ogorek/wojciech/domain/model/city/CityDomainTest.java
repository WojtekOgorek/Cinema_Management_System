package ogorek.wojciech.domain.model.city;


import ogorek.wojciech.domain.model.city.dto.GetCityDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CityDomainTest {

    @Test
    @DisplayName("when conversion to GetCityDto is correct")
    public void test1(){

        var id = 1L;
        var name = "City A";

        var city = City
                .builder()
                .id(id)
                .name(name)
                .build();

        var expectedCity = GetCityDto
                .builder()
                .id(id)
                .name(name)
                .build();

        var getCityDto = city.toGetCityDto();
        assertThat(getCityDto).isEqualTo(expectedCity);
    }

    @Test
    @DisplayName("when city has all necessary properties")
    public void test2(){
        var id = 3L;
        var name = "City Name";

        var city = City
                .builder()
                .id(id)
                .name(name)
                .build();

        assertThat(city).hasFieldOrProperty("id");
        assertThat(city).hasFieldOrProperty("name");
    }


    @Test
    @DisplayName("when city properties are correct")
    public void test3(){

        var id = 2L;
        var name = "City Name";

        var city = City
                .builder()
                .id(id)
                .name(name)
                .build();

        assertThat(city.id).isGreaterThan(0);
        assertThat(city.name).matches("[A-Z][a-z]{0,20} ?[A-Z][a-z]{0,20}");
    }

    @Test
    @DisplayName("when city is not null")
    public void test4(){
        var id = 5L;
        var name = "City Name";

        var city = City
                .builder()
                .id(id)
                .name(name)
                .build();


        assertThat(city).isNotEqualTo(null);
    }



}
