package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularDayByCitiesDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularDayByCities {
    private Long cityId;
    private Long ticketId;
    private LocalDateTime dateTime;

    public GetMostPopularDayByCitiesDto toGetMostPopularDayByCitiesDto(){
        return GetMostPopularDayByCitiesDto
                .builder()
                .cityId(cityId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

    }
}
