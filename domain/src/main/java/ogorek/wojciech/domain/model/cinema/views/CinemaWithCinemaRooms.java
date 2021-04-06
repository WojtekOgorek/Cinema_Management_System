package ogorek.wojciech.domain.model.cinema.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithCinemaRoomsDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaWithCinemaRooms {
    Long cinemaId;
    Long cinemaRoomId;

    public GetCinemaWithCinemaRoomsDto toGetCinemaWithCinemaRoomsDto() {
        return GetCinemaWithCinemaRoomsDto
                .builder()
                .cinemaId(cinemaId)
                .cinemaRoomId(cinemaRoomId)
                .build();
    }


}
