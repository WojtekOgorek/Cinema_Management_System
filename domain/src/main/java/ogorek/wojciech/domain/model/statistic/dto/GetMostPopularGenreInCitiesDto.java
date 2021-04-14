package ogorek.wojciech.domain.model.statistic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularGenreInCitiesDto {
    private Long cityId;
    private String genre;
}
