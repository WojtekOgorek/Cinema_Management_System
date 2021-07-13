package ogorek.wojciech.domain.model.cinema_room.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeatsDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaRoomWithSeats {
    private Long cinemaRoomId;
    private Long seatId;

    public GetCinemaRoomWithSeatsDto toCinemaRoomWithSeatsDto(){
        return GetCinemaRoomWithSeatsDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatId(seatId)
                .build();
    }
}
