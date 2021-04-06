package ogorek.wojciech.domain.model.cinema.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithMoviesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaWithMovies {
    Long cinemaId;
    Long movieId;
    Long seanceId;

    public GetCinemaWithMoviesDto getCinemaWithMoviesDto(){
        return GetCinemaWithMoviesDto
                .builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .seanceId(seanceId)
                .build();
    }
}
