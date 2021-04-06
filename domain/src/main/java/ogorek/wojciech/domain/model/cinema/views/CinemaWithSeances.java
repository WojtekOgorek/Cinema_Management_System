package ogorek.wojciech.domain.model.cinema.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithSeancesDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaWithSeances {
    Long cinemaId;
    Long seanceId;

    public GetCinemaWithSeancesDto getCinemaWithSeancesDto(){
        return GetCinemaWithSeancesDto
                .builder()
                .cinemaId(cinemaId)
                .seanceId(seanceId)
                .build();
    }


}
