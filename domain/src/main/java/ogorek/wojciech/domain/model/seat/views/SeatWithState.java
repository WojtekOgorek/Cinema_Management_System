package ogorek.wojciech.domain.model.seat.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.seat.dto.GetSeatWithState;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeatWithState {
    private Long seatId;
    private State state;

    public GetSeatWithState toGetSeatWithState(){
        return GetSeatWithState
                .builder()
                .seatId(seatId)
                .state(state)
                .build();
    }

}
