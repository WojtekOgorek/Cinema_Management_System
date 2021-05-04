package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {
    private Long id;
    private Long seanceId;
    private Long seatId;
    private BigDecimal price;
    private BigDecimal discount;
    private State state;
    private Long userId;

    public Ticket toTicket(){
        return Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();
    }

    public TicketEntity fromTicket(Ticket ticket){
        return TicketEntity
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();
    }
}
