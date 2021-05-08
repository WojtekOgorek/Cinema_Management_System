package ogorek.wojciech.domain.model.order.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.dto.SeatOccupancyDto;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;
import ogorek.wojciech.extension.order.dto.converter.CreateOrderDtoListJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateOrderDtoListJsonExtension.class)
@RequiredArgsConstructor
public class CreateOrderDtoListJsonTest {

    private final CreateOrderDtoListJsonConverter createOrderDtoListJsonConverter;

    @Test
    @DisplayName("when create order dto list json converter works properly")
    public void test1(){

        var username = "User";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.FAMILY).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var expectedOrder = List.of(CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build());

        var orderFromJson = createOrderDtoListJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(orderFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedOrder);
    }
}
