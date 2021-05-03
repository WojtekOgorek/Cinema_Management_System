package ogorek.wojciech.domain.model.cinema_room.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeanceDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaRoomWithSeance {
    private Long cinemaRoomId;
    private Long seanceId;

    public GetCinemaRoomWithSeanceDto toGetCinemaRoomWithSeanceDto(){
        return GetCinemaRoomWithSeanceDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seanceId(seanceId)
                .build();
    }
}
