package ogorek.wojciech.domain.model.cinema_room.views;

import lombok.*;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeatsDto;
import ogorek.wojciech.domain.model.ticket.enums.State;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CinemaRoomWithSeats {
    private Long cinemaRoomId;
    private Long seatId;
    private State state;

    public GetCinemaRoomWithSeatsDto toCinemaRoomWithSeatsDto(){
        return GetCinemaRoomWithSeatsDto
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatId(seatId)
                .state(state)
                .build();
    }
}
