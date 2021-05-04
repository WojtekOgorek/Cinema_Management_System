package ogorek.wojciech.domain.model.ticket.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;

public class CreateTicketDtoJsonConverter extends JsonConverter<CreateTicketDto> {
    public CreateTicketDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
