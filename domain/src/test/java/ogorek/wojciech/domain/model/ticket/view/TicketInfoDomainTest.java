package ogorek.wojciech.domain.model.ticket.view;

import ogorek.wojciech.domain.model.ticket.dto.GetTicketInfoDto;
import ogorek.wojciech.domain.model.ticket.views.TicketInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicketInfoDomainTest {

    @Test
    @DisplayName("when get ticket info dto conversion is correct")
    public void test1(){
        var city = "New York";
        var cinema = "Multikino";
        var cinemaRoom = "Room 1";
        var title = "Movie";
        var time = LocalDateTime.now();
        var seatRow = 5;
        var seatPlace = 10;


        var ticketInfo = TicketInfo
                .builder()
                .city(city)
                .cinema(cinema)
                .cinemaRoom(cinemaRoom)
                .title(title)
                .time(time)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .build();

        var expectedTicketInfo = GetTicketInfoDto
                .builder()
                .city(city)
                .cinema(cinema)
                .cinemaRoom(cinemaRoom)
                .title(title)
                .time(time)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .build();

        var getTicketInfoDto = ticketInfo.toGetTicketInfoDto();

        assertThat(getTicketInfoDto).isEqualTo(expectedTicketInfo);

    }


    @Test
    @DisplayName("when ticket info has all necessary properties")
    public void test2(){
        var city = "New York";
        var cinema = "Multikino";
        var cinemaRoom = "Room 1";
        var title = "Movie";
        var time = LocalDateTime.now();
        var seatRow = 5;
        var seatPlace = 10;


        var ticketInfo = TicketInfo
                .builder()
                .city(city)
                .cinema(cinema)
                .cinemaRoom(cinemaRoom)
                .title(title)
                .time(time)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .build();

        Assertions.assertAll(
                "when ticket info has all necessary properties",
                () ->assertThat(ticketInfo).hasFieldOrProperty("city"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("cinema"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("cinemaRoom"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("title"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("time"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("seatRow"),
                () ->assertThat(ticketInfo).hasFieldOrProperty("seatPlace")
        );
    }
}
