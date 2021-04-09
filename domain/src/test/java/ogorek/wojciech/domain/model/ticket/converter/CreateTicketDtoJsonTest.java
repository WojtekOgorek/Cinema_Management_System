package ogorek.wojciech.domain.model.ticket.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.domain.model.ticket.dto.converter.CreateTicketDtoJsonConverter;
import ogorek.wojciech.domain.model.ticket.enums.State;
import ogorek.wojciech.extension.ticket.dto.converter.CreateTicketDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(CreateTicketDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateTicketDtoJsonTest {
    private final CreateTicketDtoJsonConverter createTicketDtoJsonConverter;

    @Test
    @DisplayName("when create ticket dto json converter works properly")
    public void test1() {

        var seanceId = 1L;
        var seatId = 10L;
        var price = new BigDecimal("100");
        var discount = new BigDecimal("0.1");
        var state = State.FREE;
        var userId = 3L;

        var expectedTicket = List.of(
                CreateTicketDto
                        .builder()
                        .seanceId(seanceId)
                        .seatId(seatId)
                        .price(price)
                        .discount(discount)
                        .state(state)
                        .userId(userId)
                        .build());

        var ticketFromJson = createTicketDtoJsonConverter.fromJson().orElseThrow();

        Assertions.assertDoesNotThrow(() -> assertThat(ticketFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedTicket);
    }
}
