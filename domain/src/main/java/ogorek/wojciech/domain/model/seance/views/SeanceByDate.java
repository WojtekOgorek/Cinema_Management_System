package ogorek.wojciech.domain.model.seance.views;

import lombok.*;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceByDateDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SeanceByDate {
    private Long seanceId;
    private LocalDateTime dateTime;
    private Long ticketId;

    public GetSeanceByDateDto toGetSeanceByDateDto(){
        return GetSeanceByDateDto
                .builder()
                .seanceId(seanceId)
                .dateTime(dateTime)
                .ticketId(ticketId)
                .build();
    }

}
