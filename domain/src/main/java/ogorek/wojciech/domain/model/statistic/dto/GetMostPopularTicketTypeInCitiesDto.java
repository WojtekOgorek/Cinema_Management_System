package ogorek.wojciech.domain.model.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularTicketTypeInCitiesDto {
    private Long cityId;
    private State state;
}
