package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularGenreInCitiesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MostPopularGenreInCities {
    Long cityId;
    String genre;

    public GetMostPopularGenreInCitiesDto toGetMostPopularGenreInCitiesDto(){
        return GetMostPopularGenreInCitiesDto
                .builder()
                .cityId(cityId)
                .genre(genre)
                .build();
    }


}
