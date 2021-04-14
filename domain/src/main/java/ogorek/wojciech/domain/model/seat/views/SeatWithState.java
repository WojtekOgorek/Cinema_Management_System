package ogorek.wojciech.domain.model.seat.views;

import lombok.*;
import ogorek.wojciech.domain.model.seat.dto.GetSeatWithState;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SeatWithState {
    Long seatId;
    State state;

    public GetSeatWithState toGetSeatWithState(){
        return GetSeatWithState
                .builder()
                .seatId(seatId)
                .state(state)
                .build();
    }

}
