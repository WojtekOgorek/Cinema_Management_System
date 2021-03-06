package ogorek.wojciech.domain.model.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularDayByCitiesDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularDayByCities {
    private Long cityId;
    private LocalDateTime dateTime;

    public GetMostPopularDayByCitiesDto toGetMostPopularDayByCitiesDto(){
        return GetMostPopularDayByCitiesDto
                .builder()
                .cityId(cityId)
                .dateTime(dateTime)
                .build();

    }
}
