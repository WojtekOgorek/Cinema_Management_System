package ogorek.wojciech.domain.model.favourite.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateFavouriteDtoValidator implements Validator<CreateFavDto> {


    @Override
    public Map<String, String> validate(CreateFavDto createFavDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createFavDto)){
            errors.put("Favourite object is invalid:", "It is null");
            return errors;
        }
        if(!isUserIdValid(createFavDto.getUserId())){
            errors.put("Invalid id number for user:", "Id cannot be null and must be grater than 0 - " + createFavDto.getUserId());
        }
        if(!isMovieIdValid(createFavDto.getMovieId())){
            errors.put("Invalid id number for movie: ", "Id cannot be null and must be grater than 0 - " + createFavDto.getMovieId());
        }

        return errors;
    }

    private boolean isUserIdValid(Long id){
        return id != null && id > 0;
    }

    private boolean isMovieIdValid(Long id){
        return id != null && id > 0;
    }
}
