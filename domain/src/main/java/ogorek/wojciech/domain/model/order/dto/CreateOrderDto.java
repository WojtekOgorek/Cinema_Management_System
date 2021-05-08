package ogorek.wojciech.domain.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<SeatOccupancyDto> seatOccupancy;
    private State state;

}
