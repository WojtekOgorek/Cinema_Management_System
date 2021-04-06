package ogorek.wojciech.domain.model.cinema_room.dto;

import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateCinemaRoomDtoDomainTest {

    @Test
    @DisplayName("when creating cinema room is correct")
    public void test1(){

        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        var expectedCinemaRoom = CinemaRoom
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        var cinemaRoom = createCinemaRoomDto.toCinemaRoom();

        assertThat(cinemaRoom).isEqualTo(expectedCinemaRoom);
    }

    @Test
    @DisplayName("when create cinema room dto has correct properties")
    public void test2(){
        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        assertThat(createCinemaRoomDto).hasFieldOrProperty("name");
        assertThat(createCinemaRoomDto).hasFieldOrProperty("rowQuantity");
        assertThat(createCinemaRoomDto).hasFieldOrProperty("placeQuantity");
        assertThat(createCinemaRoomDto).hasFieldOrProperty("cinemaId");

    }
}
