package ogorek.wojciech.domain.model.cinema.dto;

import ogorek.wojciech.domain.model.cinema.Cinema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateCinemaDtoDomainTest {

    @Test
    @DisplayName("when creating cinema is correct")
    public void test1(){

        var name = "Multikino";
        var cityId = 2L;


        var createCinemaDto = CreateCinemaDto
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        var expectedCinema = Cinema
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        var cinema = createCinemaDto.toCinema();

        assertThat(cinema).isEqualTo(expectedCinema);
    }

    @Test
    @DisplayName("when create cinema dto has correct properties")
    public void test2(){

        var name = "Cinema City";
        var cityId = 2L;

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name(name)
                .cityId(cityId)
                .build();

        assertThat(createCinemaDto).hasOnlyFields("name", "cityId");
    }
}
