package ogorek.wojciech.domain.model.ticket;

import lombok.*;
import ogorek.wojciech.domain.model.ticket.dto.GetTicketDto;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Ticket {
    Long id;
    Long seanceId;
    Long seatId;
    BigDecimal price;
    BigDecimal discount;
    State state;
    Long userId;

    public GetTicketDto toGetTicketDto(){
        return GetTicketDto
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

    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.ONE.subtract(discount));
    }

}
