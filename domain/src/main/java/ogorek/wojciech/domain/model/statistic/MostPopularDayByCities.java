package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularDayByCitiesDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MostPopularDayByCities {
    Long cityId;
    Long ticketId;
    LocalDateTime dateTime;

    public GetMostPopularDayByCitiesDto toGetMostPopularDayByCitiesDto(){
        return GetMostPopularDayByCitiesDto
                .builder()
                .cityId(cityId)
                .ticketId(ticketId)
                .dateTime(dateTime)
                .build();

    }
}
