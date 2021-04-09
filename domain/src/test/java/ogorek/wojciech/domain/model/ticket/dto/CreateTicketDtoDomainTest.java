package ogorek.wojciech.domain.model.ticket.dto;

import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateTicketDtoDomainTest {

    @Test
    @DisplayName("when creating ticket is correct")
    public void test1(){

        var seanceId = 1L;
        var seatId = 3L;
        var price = new BigDecimal("20");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 12L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        var expectedTicket = Ticket
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        var toTicket = createTicketDto.toTicket();

        assertThat(toTicket).isEqualTo(expectedTicket);
    }

    @Test
    @DisplayName("when create ticket dto has correct properties")
    public void test2(){

        var seanceId = 1L;
        var seatId = 3L;
        var price = new BigDecimal("20");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 12L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();


        Assertions.assertAll(
                "when create ticket dto has correct properties",
                () -> assertThat(createTicketDto).hasFieldOrProperty("seanceId"),
                () -> assertThat(createTicketDto).hasFieldOrProperty("seatId"),
                () -> assertThat(createTicketDto).hasFieldOrProperty("price"),
                () -> assertThat(createTicketDto).hasFieldOrProperty("discount"),
                () -> assertThat(createTicketDto).hasFieldOrProperty("state"),
                () -> assertThat(createTicketDto).hasFieldOrProperty("userId")
        );


    }
}
