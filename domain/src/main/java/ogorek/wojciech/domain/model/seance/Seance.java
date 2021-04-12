package ogorek.wojciech.domain.model.seance;

import lombok.*;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceDto;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Seance {
    Long id;
    Long movieId;
    Long cinemaRoomId;
    LocalDateTime dateTime;

    public GetSeanceDto toGetSeanceDto(){
        return GetSeanceDto
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();
    }
}
