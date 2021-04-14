package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularTicketTypeInCitiesDto;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MostPopularTicketTypeInCities {
    Long cityId;
    State state;

    public GetMostPopularTicketTypeInCitiesDto toGetMostPopularTicketTypeInCitiesDto(){
        return GetMostPopularTicketTypeInCitiesDto
                .builder()
                .cityId(cityId)
                .state(state)
                .build();
    }
}
