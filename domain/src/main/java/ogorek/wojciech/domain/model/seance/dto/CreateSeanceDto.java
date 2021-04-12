package ogorek.wojciech.domain.model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.seance.Seance;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateSeanceDto {
    private Long movieId;
    private Long cinemaRoomId;
    private LocalDateTime dateTime;

    public Seance toSeance(){
        return Seance
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();
    }
}
