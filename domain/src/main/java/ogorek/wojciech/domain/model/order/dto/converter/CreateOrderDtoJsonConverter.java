package ogorek.wojciech.domain.model.order.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;

public class CreateOrderDtoJsonConverter extends JsonConverter<CreateOrderDto> {
    public CreateOrderDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
