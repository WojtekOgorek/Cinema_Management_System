package ogorek.wojciech.domain.model.seance.views;

import lombok.*;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceByDateDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class SeanceByDate {
    Long seanceId;
    LocalDateTime dateTime;
    Long ticketId;

    public GetSeanceByDateDto toGetSeanceByDateDto(){
        return GetSeanceByDateDto
                .builder()
                .seanceId(seanceId)
                .dateTime(dateTime)
                .ticketId(ticketId)
                .build();
    }

}
