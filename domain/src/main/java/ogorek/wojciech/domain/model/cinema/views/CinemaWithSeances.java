package ogorek.wojciech.domain.model.cinema.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithSeancesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaWithSeances {
    private Long cinemaId;
    private Long seanceId;

    public GetCinemaWithSeancesDto getCinemaWithSeancesDto(){
        return GetCinemaWithSeancesDto
                .builder()
                .cinemaId(cinemaId)
                .seanceId(seanceId)
                .build();
    }


}
