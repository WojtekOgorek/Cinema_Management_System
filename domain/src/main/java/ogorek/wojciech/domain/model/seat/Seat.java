package ogorek.wojciech.domain.model.seat;

import lombok.*;
import ogorek.wojciech.domain.model.seat.dto.GetSeatDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Seat {
    Long id;
    int seatRow;
    int seatPlace;
    Long cinemaRoomId;

    public GetSeatDto toGetSeatDto(){
        return GetSeatDto
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();
    }

}
