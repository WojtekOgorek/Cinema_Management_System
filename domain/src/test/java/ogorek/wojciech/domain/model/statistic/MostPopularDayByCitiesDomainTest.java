package ogorek.wojciech.domain.model.statistic;

import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularDayByCitiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MostPopularDayByCitiesDomainTest {

    @Test
    @DisplayName("when conversion to get most popular day by cities dto is correct")
    public void test1(){

        var cityId = 1L;
        var ticketId = 2L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15);

        var mostPopularDayByCities = MostPopularDayByCities
                .builder()
                .cityId(cityId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        var expectedDto = GetMostPopularDayByCitiesDto
                .builder()
                .cityId(cityId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        var getMostPopularDayByCitiesDto = mostPopularDayByCities.toGetMostPopularDayByCitiesDto();

        assertThat(getMostPopularDayByCitiesDto).isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when most popular day by cities has all necessary properties")
    public void test2(){

        var cityId = 1L;
        var ticketId = 2L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15);

        var mostPopularDayByCities = MostPopularDayByCities
                .builder()
                .cityId(cityId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        assertThat(mostPopularDayByCities).hasFieldOrProperty("cityId");
        assertThat(mostPopularDayByCities).hasFieldOrProperty("ticketId");
        assertThat(mostPopularDayByCities).hasFieldOrProperty("dateTime");
    }
}
