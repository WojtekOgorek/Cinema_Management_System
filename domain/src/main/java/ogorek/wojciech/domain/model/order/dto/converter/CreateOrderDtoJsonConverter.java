package ogorek.wojciech.domain.model.order.dto.converter;

import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;

import java.util.List;

public class CreateOrderDtoJsonConverter extends JsonConverter<List<CreateOrderDto>> {
    public CreateOrderDtoJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
