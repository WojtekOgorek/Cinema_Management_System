package ogorek.wojciech.domain.model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTicketDto {
    private Long seanceId;
    private Long seatId;
    private BigDecimal price;
    private BigDecimal discount;
    private State state;
    private Long userId;

    public Ticket toTicket(){
        return Ticket
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();
    }


}
