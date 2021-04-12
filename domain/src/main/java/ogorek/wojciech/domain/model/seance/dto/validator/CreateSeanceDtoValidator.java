package ogorek.wojciech.domain.model.seance.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateSeanceDtoValidator implements Validator<CreateSeanceDto> {


    @Override
    public Map<String, String> validate(CreateSeanceDto createSeanceDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createSeanceDto)){
            errors.put("Seance object is invalid:", "It is null");
            return errors;
        }

        if(!isDateValid(createSeanceDto.getDateTime())){
            errors.put("Create seance dto date is invalid:", "It cannot be null - " + createSeanceDto.getDateTime());
        }
        if(!isMovieIdValid(createSeanceDto.getMovieId())) {
            errors.put("Create seance dto invalid number for movie:", "Id cannot be null and must be greater than 0 - " + createSeanceDto.getMovieId());
        }
        if(!isCinemaRoomIdValid(createSeanceDto.getCinemaRoomId())) {
            errors.put("Create seance dto invalid number for cinema room:", "Id cannot be null and must be greater than 0 - " + createSeanceDto.getCinemaRoomId());
        }


        return errors;
    }

    private boolean isMovieIdValid(Long id){
        return id != null && id > 0;
    }

    private boolean isCinemaRoomIdValid(Long id){
        return id != null && id > 0 ;
    }

    private boolean isDateValid(LocalDateTime localDateTime){
        return localDateTime != null;
    }


}
