package ogorek.wojciech.domain.model.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularTicketTypeInCitiesDto;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularTicketTypeInCities {
    private Long cityId;
    private State state;

    public GetMostPopularTicketTypeInCitiesDto toGetMostPopularTicketTypeInCitiesDto(){
        return GetMostPopularTicketTypeInCitiesDto
                .builder()
                .cityId(cityId)
                .state(state)
                .build();
    }
}
