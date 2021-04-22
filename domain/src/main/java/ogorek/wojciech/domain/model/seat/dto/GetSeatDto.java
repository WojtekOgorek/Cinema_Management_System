package ogorek.wojciech.domain.model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetSeatDto {
    private Long id;
    private int seatRow;
    private int seatPlace;
    private Long cinemaRoomId;
}
