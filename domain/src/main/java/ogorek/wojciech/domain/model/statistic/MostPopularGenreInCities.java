package ogorek.wojciech.domain.model.statistic;

import lombok.*;
import ogorek.wojciech.domain.model.statistic.dto.GetMostPopularGenreInCitiesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MostPopularGenreInCities {
    private Long cityId;
    private String genre;

    public GetMostPopularGenreInCitiesDto toGetMostPopularGenreInCitiesDto(){
        return GetMostPopularGenreInCitiesDto
                .builder()
                .cityId(cityId)
                .genre(genre)
                .build();
    }


}
