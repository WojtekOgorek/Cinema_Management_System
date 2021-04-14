package ogorek.wojciech.domain.model.order.dto;

import lombok.*;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateOrderDto {
    private String username;
    private Long seanceId;
    private List<Long> seatIds;
    private List<Occupancy> occupancies;
    private State state;

}
