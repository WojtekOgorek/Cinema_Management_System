package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.movie.Movie;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {

    private Long id;
    private String title;
    private String genre;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Movie toMovie(){
        return Movie
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public MovieEntity fromMovie(Movie movie){
        return MovieEntity
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
