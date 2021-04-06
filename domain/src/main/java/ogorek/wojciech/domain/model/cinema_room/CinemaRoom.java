package ogorek.wojciech.domain.model.cinema_room;

import lombok.*;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaRoom {
    Long id;
    String name;
    int rowQuantity;
    int placeQuantity;
    Long cinemaId;

    public GetCinemaRoomDto toGetCinemaRoomDto(){
        return GetCinemaRoomDto
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();
    }

}
