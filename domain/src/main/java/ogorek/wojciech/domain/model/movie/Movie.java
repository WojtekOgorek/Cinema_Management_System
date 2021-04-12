package ogorek.wojciech.domain.model.movie;

import lombok.*;
import ogorek.wojciech.domain.model.movie.dto.GetMovieDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Movie {
    Long id;
    String title;
    String genre;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public GetMovieDto toGetMovieDto(){
        return GetMovieDto
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
