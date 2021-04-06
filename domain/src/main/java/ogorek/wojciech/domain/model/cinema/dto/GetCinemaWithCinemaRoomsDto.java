package ogorek.wojciech.domain.model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetCinemaWithCinemaRoomsDto {
    private Long cinemaId;
    private Long cinemaRoomId;
}
