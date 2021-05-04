package ogorek.wojciech.infrastructure.repository.entity;

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
public class SeanceEntity {
    private Long id;
    private Long movieId;
    private Long cinemaRoomId;
    private LocalDateTime dateTime;

    public Seance toSeance(){
        return Seance
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();
    }

    public SeanceEntity fromSeance(Seance seance){
        return SeanceEntity
                .builder()
                .id(id)
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();
    }
}
