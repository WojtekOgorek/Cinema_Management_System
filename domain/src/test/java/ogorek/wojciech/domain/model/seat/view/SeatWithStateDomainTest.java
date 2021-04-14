package ogorek.wojciech.domain.model.seat.view;

import ogorek.wojciech.domain.model.seat.dto.GetSeatWithState;
import ogorek.wojciech.domain.model.seat.views.SeatWithState;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeatWithStateDomainTest {

    @Test
    @DisplayName("when conversion to get seat with state is correct")
    public void test1(){

        var seatId = 1L;
        var state = State.FREE;

        var seatWithState = SeatWithState
                .builder()
                .seatId(seatId)
                .state(state)
                .build();

        var expectedSeatWithState = GetSeatWithState
                .builder()
                .seatId(seatId)
                .state(state)
                .build();

        var getSeatWithState = seatWithState.toGetSeatWithState();

        assertThat(getSeatWithState).isEqualTo(expectedSeatWithState);
    }

    @Test
    @DisplayName("when seat with state haas all necessary properties")
    public void test2(){

        var seatId = 1L;
        var state = State.FREE;

        var seatWithState = SeatWithState
                .builder()
                .seatId(seatId)
                .state(state)
                .build();

        assertThat(seatWithState).hasOnlyFields("seatId", "state");
    }
}
