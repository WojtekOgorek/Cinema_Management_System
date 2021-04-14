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
        var seatIds = List.of(1L,2L,3L);
        var occupancies = List.of(Occupancy.MINOR,Occupancy.MINOR,Occupancy.MINOR);
        var state = State.RESERVED;

        var createOrderDto = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatIds(seatIds)
                .occupancies(occupancies)
                .state(state)
                .build();

        assertThat(createOrderDto).hasFieldOrProperty("username");
        assertThat(createOrderDto).hasFieldOrProperty("seanceId");
        assertThat(createOrderDto).hasFieldOrProperty("seatIds");
        assertThat(createOrderDto).hasFieldOrProperty("occupancies");
        assertThat(createOrderDto).hasFieldOrProperty("state");
    }
}
