package ogorek.wojciech.domain.model.movie.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateMovieDtoValidator implements Validator<CreateMovieDto> {

    @Override
    public Map<String, String> validate(CreateMovieDto createMovieDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createMovieDto)){
            errors.put("Create movie dto object is invalid:", "It is null");
            return errors;
        }
        if(!isMovieTitleValid(createMovieDto.getTitle())){
            errors.put("Create movie dto title is invalid:", "Title must contains only 40 letter, numbers, white spaces - " + createMovieDto.getTitle());
        }
        if(!isMovieGenreValid(createMovieDto.getGenre())){
            errors.put("Create movie dto genre is invalid:", "Genre must contains only 40 letter, numbers, white spaces - " + createMovieDto.getGenre());
        }
        if(isMovieDateValid(createMovieDto.getStartDate())){
            errors.put("Create movie dto start date is is invalid:", "Date cannot be null - " + createMovieDto.getStartDate());
        }
        if(isMovieDateValid(createMovieDto.getEndDate())){
            errors.put("Create movie dto end date is is invalid:", "Date cannot be null - " + createMovieDto.getStartDate());
        }

        return errors;
    }

    private boolean isMovieTitleValid(String title){
        return title != null && title.matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
    }
    private boolean isMovieGenreValid(String genre){
        return genre != null && genre.matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
    }

    private boolean isMovieDateValid(LocalDateTime localDateTime){
        return localDateTime == null;
    }



}
