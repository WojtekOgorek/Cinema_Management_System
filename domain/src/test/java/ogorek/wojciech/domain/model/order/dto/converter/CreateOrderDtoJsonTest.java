package ogorek.wojciech.domain.model.order.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
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
public class CreateOrderDtoJsonTest{

    private final CreateOrderDtoJsonConverter createOrderDtoJsonConverter;

    @Test
    @DisplayName("when create order dto json converter works properly")
    public void test1(){

        var username = "User";
        var seanceId = 1L;
        var seatIds = List.of(1L,2L,3L);
        var occupancies = List.of(Occupancy.FAMILY,Occupancy.GROUP,Occupancy.MINOR);
        var state = State.RESERVED;

        var expectedOrder = List.of(CreateOrderDto
                .builder()
                .username(username)
                .seanceId(seanceId)
                .seatIds(seatIds)
                .occupancies(occupancies)
                .state(state)
                .build());

        var orderFromJson = createOrderDtoJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(orderFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedOrder);
    }
}
