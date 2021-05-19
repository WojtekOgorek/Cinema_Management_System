package ogorek.wojciech.domain.model.order.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.dto.SeatOccupancyDto;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateOrderDtoValidatorTest {

    @Test
    @DisplayName("when create order dto is null")
    public void test1() {

        assertThatThrownBy(() -> Validator.validate(new CreateOrderDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create order dto object is invalid:");
    }

    @Test
    @DisplayName("when create order dto username is invalid")
    public void test2() {

        var username = "user";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.SENIOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateOrderDtoValidator(), createOrderDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create order dto username is invalid:");
    }

    @Test
    @DisplayName("when create order dto seance id is invalid")
    public void test3() {

        var username = "User";
        var seanceId = 0L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.SENIOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateOrderDtoValidator(), createOrderDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create order dto seance id is invalid:");
    }

    @Test
    @DisplayName("when create order dto seat id is invalid")
    public void test4() {

        var username = "User";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(0L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.SENIOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateOrderDtoValidator(), createOrderDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create order dto seat id is invalid:");
    }


    @Test
    @DisplayName("when create order dto occupancy is invalid")
    public void test6() {
        //todo check it
    }


    @Test
    @DisplayName("when create order dto state invalid")
    public void test7() {

        var username = "User";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.SENIOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        State state = null;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateOrderDtoValidator(), createOrderDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create order dto state is invalid:");
    }


}

