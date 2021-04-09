package ogorek.wojciech.domain.model.ticket.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.city.converter.AppConverterException;
import ogorek.wojciech.domain.model.ticket.dto.converter.CreateTicketDtoJsonConverter;
import ogorek.wojciech.extension.ticket.dto.converter.CreateTicketDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateTicketDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateTicketDtoJsonErrorTest {

    private final CreateTicketDtoJsonConverter createTicketDtoJsonConverter;

    @Test
    @DisplayName("when create ticket dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createTicketDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }

}
