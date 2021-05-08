package ogorek.wojciech.domain.model.order.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.dto.SeatOccupancyDto;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;
import ogorek.wojciech.extension.order.dto.converter.CreateOrderDtoJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateOrderDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateOrderDtoJsonTest {

    private final CreateOrderDtoJsonConverter createOrderDtoJsonConverter;

    @Test
    @DisplayName("when create order dto json converter works properly")
    public void test1(){

        var username = "User";
        var seanceId = 1L;
        var seatOccupancy = List.of(
                SeatOccupancyDto.builder().seatId(1L).occupancy(Occupancy.FAMILY).build(),
                SeatOccupancyDto.builder().seatId(2L).occupancy(Occupancy.MINOR).build(),
                SeatOccupancyDto.builder().seatId(3L).occupancy(Occupancy.REGULAR).build());
        var state = State.RESERVED;

        var expectedOrder = CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatOccupancy(seatOccupancy)
                .state(state)
                .build();

        var orderFromJson = createOrderDtoJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(orderFromJson))
                .isEqualTo(expectedOrder);
    }
}
