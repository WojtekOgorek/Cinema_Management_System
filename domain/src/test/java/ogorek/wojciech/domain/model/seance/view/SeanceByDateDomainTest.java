package ogorek.wojciech.domain.model.seance.view;

import ogorek.wojciech.domain.model.seance.dto.GetSeanceByDateDto;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SeanceByDateDomainTest {
    @Test
    @DisplayName("when conversion to get seance by date is correct")
    public void test1(){
        var seanceId = 1L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15,15,15);
        var ticketId = 3L;

        var seanceByDate = SeanceByDate
                .builder()
                .seanceId(seanceId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        var expectedSeanceByDate = GetSeanceByDateDto
                .builder()
                .seanceId(seanceId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        var getSeanceByDateDto = seanceByDate.toGetSeanceByDateDto();

        assertThat(getSeanceByDateDto).isEqualTo(expectedSeanceByDate);
    }

    @Test
    @DisplayName("when seance by date has correct properties")
    public void test2(){

        var seanceId = 1L;
        var dateTime = LocalDateTime.of(2021,1,1,12,15,15,15);
        var ticketId = 3L;

        var seanceByDate = SeanceByDate
                .builder()
                .seanceId(seanceId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

        assertThat(seanceByDate).hasFieldOrProperty("seanceId");
        assertThat(seanceByDate).hasFieldOrProperty("dateTime");
        assertThat(seanceByDate).hasFieldOrProperty("ticketId");
    }
}
