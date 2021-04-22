package ogorek.wojciech.domain.model.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularGenreInCitiesDto {
    private Long cityId;
    private String genre;
}
