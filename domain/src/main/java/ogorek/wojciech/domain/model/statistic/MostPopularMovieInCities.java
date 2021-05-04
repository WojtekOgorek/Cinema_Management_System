package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularMovieInCitiesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularMovieInCities {
    private Long movieId;
    private Long cityId;

    public GetMostPopularMovieInCitiesDto toGetMostPopularMovieInCitiesDto(){
        return GetMostPopularMovieInCitiesDto
                .builder()
                .movieId(movieId)
                .cityId(cityId)
                .build();
    }
}
