package ogorek.wojciech.domain.model.ticket;


import ogorek.wojciech.domain.model.ticket.dto.GetTicketDto;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicketDomainTest {

    @Test
    @DisplayName("when conversion to get ticket dto is correct")
    public void test1(){

        var id = 1L;
        var seanceId = 2L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var ticket = Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        var expectedTicket = GetTicketDto
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        var getTicketDto = ticket.toGetTicketDto();

        assertThat(getTicketDto).isEqualTo(expectedTicket);
    }

    @Test
    @DisplayName("when ticket total price method is correct")
    public void test2(){
        var id = 1L;
        var seanceId = 2L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var ticket = Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        var ticketPrice = price.multiply(BigDecimal.ONE.subtract(discount));

        var expectedTicketPrice = ticket.totalPrice();

        assertThat(ticketPrice).isEqualTo(expectedTicketPrice);
    }

    @Test
    @DisplayName("when ticket has all necessary properties")
    public void test3(){
        var id = 1L;
        var seanceId = 2L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var ticket = Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThat(ticket).hasFieldOrProperty("id");
        assertThat(ticket).hasFieldOrProperty("seanceId");
        assertThat(ticket).hasFieldOrProperty("seatId");
        assertThat(ticket).hasFieldOrProperty("price");
        assertThat(ticket).hasFieldOrProperty("discount");
        assertThat(ticket).hasFieldOrProperty("state");
        assertThat(ticket).hasFieldOrProperty("userId");
    }

    @Test
    @DisplayName("when ticket properties are correct")
    public void test4(){

        var id = 1L;
        var seanceId = 2L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var ticket = Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThat(ticket.id).isGreaterThan(0);
        assertThat(ticket.seanceId).isGreaterThan(0);
        assertThat(ticket.seatId).isGreaterThan(0);
        assertThat(ticket.price).isInstanceOf(BigDecimal.class).isGreaterThan(BigDecimal.ZERO);
        assertThat(ticket.discount).isInstanceOf(BigDecimal.class).isBetween(BigDecimal.ZERO, BigDecimal.ONE);
        assertThat(ticket.state).isInstanceOf(State.class);
        assertThat(ticket.userId).isGreaterThan(0);
    }

    @Test
    @DisplayName("when ticket is not null")
    public void test5(){

        var id = 1L;
        var seanceId = 2L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var ticket = Ticket
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThat(ticket).isNotEqualTo(null);
    }
}
