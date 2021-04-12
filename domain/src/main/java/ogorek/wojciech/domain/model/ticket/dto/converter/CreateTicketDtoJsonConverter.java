package ogorek.wojciech.domain.model.ticket.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;

import java.util.List;

public class CreateTicketDtoJsonConverter extends JsonConverter<List<CreateTicketDto>> {
    public CreateTicketDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
