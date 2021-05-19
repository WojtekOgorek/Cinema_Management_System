package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.seat.Seat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeatEntity {
    private Long id;
    private int seatRow;
    private int seatPlace;
    private Long cinemaRoomId;

    public Seat toSeat(){
        return Seat
                .builder()
                .id(id)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .cinemaRoomId(cinemaRoomId)
                .build();
    }

    public SeatEntity fromSeat(Seat seat){
        var toGetSeatDto = seat.toGetSeatDto();
        return SeatEntity
                .builder()
                .id(toGetSeatDto.getId())
                .seatRow(toGetSeatDto.getSeatRow())
                .seatPlace(toGetSeatDto.getSeatPlace())
                .cinemaRoomId(toGetSeatDto.getCinemaRoomId())
                .build();
    }
}
