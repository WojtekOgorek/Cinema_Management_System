package ogorek.wojciech.domain.model.cinema.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateCinemaDtoValidator implements Validator<CreateCinemaDto> {

    @Override
    public Map<String, String> validate(CreateCinemaDto createCinemaDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createCinemaDto)){
            errors.put("Cinema object:", "is null");
            return errors;
        }
        if(!isCinemaNameValid(createCinemaDto.getName())){
            errors.put("Cinema name is invalid:", "It must begin with uppercase and no more than 40 letters " + createCinemaDto.getName());
        }
        if(!isCityIdValid(createCinemaDto.getCityId())){
            errors.put("Cinema city id is invalid:", "It must be greater than 0 " + createCinemaDto.getCityId());
        }

        return errors;
    }


    private boolean isCinemaNameValid(String name){
        return name != null && name.matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
    }

    private boolean isCityIdValid(Long cityId){
        return cityId != null && cityId > 0;
    }
}
