package ogorek.wojciech.domain.model.cinema.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithMoviesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaWithMovies {
    private Long cinemaId;
    private Long movieId;
    private Long seanceId;

    public GetCinemaWithMoviesDto getCinemaWithMoviesDto(){
        return GetCinemaWithMoviesDto
                .builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .seanceId(seanceId)
                .build();
    }
}
