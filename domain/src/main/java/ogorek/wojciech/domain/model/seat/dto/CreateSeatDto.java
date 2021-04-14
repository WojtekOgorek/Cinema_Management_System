package ogorek.wojciech.domain.model.seat.dto;

import lombok.*;
import ogorek.wojciech.domain.model.seat.Seat;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSeatDto {
    private Long cinemaRoomId;
    private int seatRow;
    private int seatPlace;

    public Seat toSeat(){
        return Seat
                .builder()
                .cinemaRoomId(cinemaRoomId)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .build();
    }

}
