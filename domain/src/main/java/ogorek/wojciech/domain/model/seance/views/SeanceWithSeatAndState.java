package ogorek.wojciech.domain.model.seance.views;

import lombok.*;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceWithSeatAndStateDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeanceWithSeatAndState {
    private Long seanceId;
    private Long seatId;
    private Long ticketId;

    public GetSeanceWithSeatAndStateDto toGetSeanceWithSeatAndState(){
        return GetSeanceWithSeatAndStateDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .ticketId(ticketId)
                .build();
    }
}
