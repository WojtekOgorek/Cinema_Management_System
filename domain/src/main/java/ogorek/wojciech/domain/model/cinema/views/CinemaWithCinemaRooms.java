package ogorek.wojciech.domain.model.cinema.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaWithCinemaRoomsDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaWithCinemaRooms {
    private Long cinemaId;
    private Long cinemaRoomId;

    public GetCinemaWithCinemaRoomsDto toGetCinemaWithCinemaRoomsDto() {
        return GetCinemaWithCinemaRoomsDto
                .builder()
                .cinemaId(cinemaId)
                .cinemaRoomId(cinemaRoomId)
                .build();
    }


}
