package ogorek.wojciech.domain.model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.movie.Movie;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateMovieDto {
    private String title;
    private String genre;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Movie toMovie(){
        return Movie
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
