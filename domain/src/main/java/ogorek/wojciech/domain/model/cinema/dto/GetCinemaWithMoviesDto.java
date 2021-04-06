package ogorek.wojciech.domain.model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetCinemaWithMoviesDto {
    private Long cinemaId;
    private Long movieId;
    private Long seanceId;
}
