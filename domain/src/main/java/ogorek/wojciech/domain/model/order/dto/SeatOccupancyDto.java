package ogorek.wojciech.domain.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.order.enums.Occupancy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatOccupancyDto {
    private Long seatId;
    private Occupancy occupancy;
}
