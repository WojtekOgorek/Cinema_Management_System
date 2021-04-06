package ogorek.wojciech.domain.model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetTicketDto {
    private Long id;
    private Long seanceId;
    private Long seatId;
    private BigDecimal price;
    private BigDecimal discount;
    private State state;
    private Long userId;
}
