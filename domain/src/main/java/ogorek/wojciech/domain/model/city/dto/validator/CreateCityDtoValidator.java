package ogorek.wojciech.domain.model.city.dto.validator;


import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateCityDtoValidator implements Validator<CreateCityDto> {
    @Override
    public Map<String, String> validate(CreateCityDto createCityDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createCityDto)){
            errors.put("City object:", "is null");
            return errors;
        }
        if(!isCityNameValid(createCityDto.getName())){
            errors.put("City name is invalid:", "It must begin with uppercase " + createCityDto.getName());
        }

        return errors;
    }

    private boolean isCityNameValid(String cityName){
        return cityName != null && cityName.matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
    }
}
