package ogorek.wojciech.domain.model.statistic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMostPopularMovieInCitiesDto {
    private Long cityId;
    private Long movieId;

}
