package ogorek.wojciech.domain.model.ticket.views;

import lombok.*;
import ogorek.wojciech.domain.model.ticket.dto.GetTicketInfoDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class TicketInfo {
    String city;
    String cinema;
    String cinemaRoom;
    String title;
    LocalDateTime time;
    int seatRow;
    int seatPlace;

    public GetTicketInfoDto toGetTicketInfoDto(){
        return GetTicketInfoDto
                .builder()
                .city(city)
                .cinema(cinema)
                .cinemaRoom(cinemaRoom)
                .title(title)
                .time(time)
                .seatRow(seatRow)
                .seatPlace(seatPlace)
                .build();
    }
}
