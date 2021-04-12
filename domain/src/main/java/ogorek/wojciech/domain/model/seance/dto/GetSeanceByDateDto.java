package ogorek.wojciech.domain.model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetSeanceByDateDto {
    private Long seanceId;
    private LocalDateTime dateTime;
    private Long ticketId;

}
