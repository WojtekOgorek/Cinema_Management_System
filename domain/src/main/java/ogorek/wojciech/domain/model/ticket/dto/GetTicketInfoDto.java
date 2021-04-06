package ogorek.wojciech.domain.model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTicketInfoDto {
    private String city;
    private String cinema;
    private String cinemaRoom;
    private String title;
    private LocalDateTime time;
    private int seatRow;
    private int seatPlace;
}
