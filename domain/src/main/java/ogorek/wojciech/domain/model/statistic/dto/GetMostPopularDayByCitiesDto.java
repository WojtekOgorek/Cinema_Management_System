package ogorek.wojciech.domain.model.statistic.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularDayByCitiesDto {
    private Long cityId;
    private Long ticketId;
    private LocalDateTime dateTime;
}
