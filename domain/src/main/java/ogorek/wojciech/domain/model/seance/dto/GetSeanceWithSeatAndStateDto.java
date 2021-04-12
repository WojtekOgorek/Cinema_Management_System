package ogorek.wojciech.domain.model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeanceWithSeatAndStateDto {
    private Long seanceId;
    private Long seatId;
    private Long ticketId;
}
