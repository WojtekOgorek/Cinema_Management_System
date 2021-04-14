package ogorek.wojciech.domain.model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.ticket.enums.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeatWithState {
    private Long seatId;
    private State state;

}
