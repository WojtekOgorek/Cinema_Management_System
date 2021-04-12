package ogorek.wojciech.domain.model.seance.view;

import ogorek.wojciech.domain.model.seance.dto.GetSeanceWithSeatAndStateDto;
import ogorek.wojciech.domain.model.seance.views.SeanceWithSeatAndState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeanceWithSeatAndStateDomainTest {

    @Test
    @DisplayName("when conversion to seance with seat and state dto is correct")
    public void test1(){
        var seanceId = 1L;
        var seatId = 2L;
        var ticketId = 3L;

        var seanceWithSeatAndState = SeanceWithSeatAndState
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .ticketId(ticketId)
                .build();

        var expectedSeanceWithSeatAndState = GetSeanceWithSeatAndStateDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .ticketId(ticketId)
                .build();

        var getSeanceWithSeatAndStateDto = seanceWithSeatAndState.toGetSeanceWithSeatAndState();

        assertThat(getSeanceWithSeatAndStateDto).isEqualTo(expectedSeanceWithSeatAndState);

    }

    @Test
    @DisplayName("when seance with seat and state has all necessary properties")
    public void test2(){
        var seanceId = 1L;
        var seatId = 2L;
        var ticketId = 3L;

        var seanceWithSeatAndState = SeanceWithSeatAndState
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .ticketId(ticketId)
                .build();

        assertThat(seanceWithSeatAndState).hasOnlyFields("seanceId", "seatId", "ticketId");
    }
}
