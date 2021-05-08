package ogorek.wojciech.domain.model.order.dto;

import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateOrderDtoDomainTest {

    @Test
    @DisplayName("when create order dto has all necessary properties")
    public void test1(){

        var username = "User";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.FAMILY).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        assertThat(createOrderDto).hasFieldOrProperty("username");
        assertThat(createOrderDto).hasFieldOrProperty("seanceId");
        assertThat(createOrderDto).hasFieldOrProperty("seatOccupancy");
        assertThat(createOrderDto).hasFieldOrProperty("state");
    }
}
