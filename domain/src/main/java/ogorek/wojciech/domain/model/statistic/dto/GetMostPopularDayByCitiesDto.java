package ogorek.wojciech.domain.model.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularDayByCitiesDto {
    private Long cityId;
    private LocalDateTime dateTime;
}
