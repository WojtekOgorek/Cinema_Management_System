package ogorek.wojciech.domain.model.seance.views;

import lombok.*;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceWithSeatAndStateDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SeanceWithSeatAndState {
    Long seanceId;
    Long seatId;
    Long ticketId;

    public GetSeanceWithSeatAndStateDto toGetSeanceWithSeatAndState(){
        return GetSeanceWithSeatAndStateDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .ticketId(ticketId)
                .build();
    }
}
