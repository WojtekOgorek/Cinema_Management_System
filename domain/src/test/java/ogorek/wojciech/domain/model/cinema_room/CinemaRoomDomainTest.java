package ogorek.wojciech.domain.model.cinema_room;

import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CinemaRoomDomainTest {

    @Test
    @DisplayName("when conversion to get cinema room dto is correct")
    public void test1(){

        var id = 1L;
        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var cinemaRoom = CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        var expectedCinemaRoom = GetCinemaRoomDto
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        var getCinemaRoomDto = cinemaRoom.toGetCinemaRoomDto();

        assertThat(getCinemaRoomDto).isEqualTo(expectedCinemaRoom);
    }

    @Test
    @DisplayName("when cinema room has all necessary properties")
    public void test2(){

        var id = 1L;
        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var cinemaRoom = CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        assertThat(cinemaRoom).hasFieldOrProperty("id");
        assertThat(cinemaRoom).hasFieldOrProperty("name");
        assertThat(cinemaRoom).hasFieldOrProperty("rowQuantity");
        assertThat(cinemaRoom).hasFieldOrProperty("placeQuantity");
        assertThat(cinemaRoom).hasFieldOrProperty("cinemaId");
    }

    @Test
    @DisplayName("when cinema room properties are correct")
    public void test3(){
        var id = 1L;
        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var cinemaRoom = CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        assertThat(cinemaRoom.id).isGreaterThan(0);
        assertThat(cinemaRoom.name).matches("[A-Za-z\\d][A-Za-z\\d\\s]{0,50}");
        assertThat(cinemaRoom.rowQuantity).isGreaterThan(0);
        assertThat(cinemaRoom.placeQuantity).isGreaterThan(0);
        assertThat(cinemaRoom.cinemaId).isGreaterThan(0);
    }

    @Test
    @DisplayName("when cinema room is not null")
    public void test4(){
        var id = 1L;
        var name = "First room";
        var rowQuantity = 5;
        var placeQuantity = 10;
        var cinemaId = 3L;

        var cinemaRoom = CinemaRoom
                .builder()
                .id(id)
                .name(name)
                .rowQuantity(rowQuantity)
                .placeQuantity(placeQuantity)
                .cinemaId(cinemaId)
                .build();

        assertThat(cinemaRoom).isNotEqualTo(null);
    }
}
