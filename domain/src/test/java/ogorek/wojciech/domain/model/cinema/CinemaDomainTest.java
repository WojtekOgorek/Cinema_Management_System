package ogorek.wojciech.domain.model.cinema;

import ogorek.wojciech.domain.model.cinema.dto.GetCinemaDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;


public class CinemaDomainTest {

    @Test
    @DisplayName("when conversion to get cinema dto is correct")
    public void test1(){

        var id = 1L;
        var name = "Abc";
        var cityId = 3L;

        var cinema = Cinema
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();

        var expectedCinema = GetCinemaDto
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();

        var getCinemaDto = cinema.toGetCinemaDto();

        assertThat(getCinemaDto).isEqualTo(getCinemaDto);
    }

    @Test
    @DisplayName("when cinema has all necessary properties")
    public void test2(){

        var id = 2L;
        var name = "Multi";
        var cityId = 43L;

        var cinema = Cinema
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();

        assertThat(cinema).hasFieldOrProperty("id");
        assertThat(cinema).hasFieldOrProperty("name");
        assertThat(cinema).hasFieldOrProperty("cityId");
    }

    @Test
    @DisplayName("when cinema properties are correct")
    public void test3(){

        var id = 2L;
        var name = "Multi";
        var cityId = 43L;

        var cinema = Cinema
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();

        assertThat(cinema.id).isGreaterThan(0);
        assertThat(cinema.name).matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
        assertThat(cinema.cityId).isGreaterThan(0);
    }

    @Test
    @DisplayName("when cinema is not null")
    public void test4(){

        var id = 5L;
        var name = "Multi";
        var cityId = 4L;

        var cinema = Cinema
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();


        assertThat(cinema).isNotEqualTo(null);
    }
}
