package ogorek.wojciech.domain.model.ticket.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CreateTicketDtoValidatorTest {

    @Test
    @DisplayName("when create ticket dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Ticket object is invalid:");
    }

    @Test
    @DisplayName("when create ticket dto user id is invalid")
    public void test2(){

        var seanceId = 1L;
        var seatId = 3L;
        var price = new BigDecimal("20");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 0L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Invalid id number for user:");
    }

    @Test
    @DisplayName("when create ticket dto seance id is invalid")
    public void test3() {

        var seanceId = -1L;
        var seatId = 3L;
        var price = new BigDecimal("20");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 2L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Invalid id number for seance:");
    }

    @Test
    @DisplayName("when create ticket dto seat id is invalid")
    public void test4() {

        var seanceId = 6L;
        var seatId = -3L;
        var price = new BigDecimal("20");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 2L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Invalid id number for seat:");
    }

    @Test
    @DisplayName("when create ticket dto price is invalid")
    public void test5() {

        var seanceId = 6L;
        var seatId = 3L;
        var price = new BigDecimal("-100");
        var discount = new BigDecimal("0.2");
        var state = State.BOUGHT;
        var userId = 2L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Ticket price is invalid:");
    }

    @Test
    @DisplayName("when create ticket dto discount is invalid")
    public void test6() {

        var seanceId = 6L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("-1");
        var state = State.BOUGHT;
        var userId = 2L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Ticket discount is invalid:");
    }

    @Test
    @DisplayName("when create ticket dto state is invalid")
    public void test7() {

        var seanceId = 6L;
        var seatId = 3L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.2");
        State state = null;
        var userId = 2L;

        var createTicketDto = CreateTicketDto
                .builder()
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateTicketDtoValidator(), createTicketDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Ticket state is invalid:");
    }
}
