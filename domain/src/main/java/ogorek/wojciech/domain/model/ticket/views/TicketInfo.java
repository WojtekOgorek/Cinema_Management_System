package ogorek.wojciech.domain.model.ticket.views;

import lombok.*;
import ogorek.wojciech.domain.model.ticket.dto.GetTicketInfoDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketInfo {
    private String city;
    private String cinema;
    private String cinemaRoom;
    private String title;
    private LocalDateTime time;
    private int seatRow;
    private int seatPlace;

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
