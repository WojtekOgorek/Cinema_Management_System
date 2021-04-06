package ogorek.wojciech.domain.model.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCinemaRoomWithSeanceDto {
    private Long cinemaRoomId;
    private Long seanceId;
}
