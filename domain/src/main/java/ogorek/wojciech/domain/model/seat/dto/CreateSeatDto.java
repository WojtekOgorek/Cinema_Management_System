package ogorek.wojciech.domain.model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
