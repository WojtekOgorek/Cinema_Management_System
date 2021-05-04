package ogorek.wojciech.domain.model.ticket.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.ticket.dto.converter.CreateTicketDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateTicketDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateTicketDtoListJsonErrorTest {

    private final CreateTicketDtoListJsonConverter createTicketDtoListJsonConverter;

    @Test
    @DisplayName("when create ticket dto json converter throws exception")
    public void test1(){

        assertThatThrownBy(() -> createTicketDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("NumberFormatException");
    }

}
