package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularTicketTypeInCitiesDto;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MostPopularTicketTypeInCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get most popular ticket type in cities dto is correct")
    public void test1(){

        var cityId = 1L;
        var state = State.RESERVED;

        var mostPopularTicketTypeInCities = MostPopularTicketTypeInCities
                .builder()
                .cityId(cityId)
                .state(state)
                .build();

        var expectedDto = GetMostPopularTicketTypeInCitiesDto
                .builder()
                .cityId(cityId)
                .state(state)
                .build();

        var getMostPopularTicketTypeInCitiesDto = mostPopularTicketTypeInCities.toGetMostPopularTicketTypeInCitiesDto();

        assertThat(getMostPopularTicketTypeInCitiesDto).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when most popular ticket type in cities has all necessary properties")
    public void test2(){

        var cityId = 1L;
        var state = State.RESERVED;

        var mostPopularTicketTypeInCities = MostPopularTicketTypeInCities
                .builder()
                .cityId(cityId)
                .state(state)
                .build();


        assertThat(mostPopularTicketTypeInCities).hasFieldOrProperty("cityId");
        assertThat(mostPopularTicketTypeInCities).hasFieldOrProperty("state");
    }
}
