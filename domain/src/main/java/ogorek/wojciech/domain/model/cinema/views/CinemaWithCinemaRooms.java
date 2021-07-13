package ogorek.wojciech.domain.model.cinema.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
