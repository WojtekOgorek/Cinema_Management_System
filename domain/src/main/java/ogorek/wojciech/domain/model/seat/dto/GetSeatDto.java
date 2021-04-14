package ogorek.wojciech.domain.model.seat.dto;

import lombok.*;

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
