package ogorek.wojciech.domain.model.ticket.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;

import java.util.List;

public class CreateTicketDtoListJsonConverter extends JsonConverter<List<CreateTicketDto>> {
    public CreateTicketDtoListJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
