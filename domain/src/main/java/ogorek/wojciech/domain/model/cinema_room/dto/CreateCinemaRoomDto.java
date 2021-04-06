package ogorek.wojciech.domain.model.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaRoomDto {
    private String name;
    private int rowQuantity;
    private int placeQuantity;
    private Long cinemaId;

    public CinemaRoom toCinemaRoom(){
        return CinemaRoom
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();
    }
}
