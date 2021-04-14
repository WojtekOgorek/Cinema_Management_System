package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularMovieInCitiesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MostPopularMovieInCities {
    Long movieId;
    Long cityId;

    public GetMostPopularMovieInCitiesDto toGetMostPopularMovieInCitiesDto(){
        return GetMostPopularMovieInCitiesDto
                .builder()
                .movieId(movieId)
                .cityId(cityId)
                .build();
    }
}
