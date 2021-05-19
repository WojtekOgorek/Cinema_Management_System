package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaRoomEntity {
    private Long id;
    private String name;
    private int rowQuantity;
    private int placeQuantity;
    private Long cinemaId;

    public CinemaRoom toCinemaRoom(){
        return CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();
    }

    public CinemaRoomEntity fromCinemaRoom(CinemaRoom cinemaRoom){
        var toGetCinemaRoomDto = cinemaRoom.toGetCinemaRoomDto();
        return CinemaRoomEntity
                .builder()
                .id(toGetCinemaRoomDto.getId())
                .name(toGetCinemaRoomDto.getName())
                .rowQuantity(toGetCinemaRoomDto.getRowQuantity())
                .placeQuantity(toGetCinemaRoomDto.getPlaceQuantity())
                .cinemaId(toGetCinemaRoomDto.getCinemaId())
                .build();
    }
}
