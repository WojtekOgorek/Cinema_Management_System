package ogorek.wojciech.domain.model.cinema_room.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateCinemaRoomDtoValidator implements Validator<CreateCinemaRoomDto> {

    @Override
    public Map<String, String> validate(CreateCinemaRoomDto createCinemaRoomDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createCinemaRoomDto)){
            errors.put("Cinema room object:", "is null");
            return errors;
        }
        if(!isCinemaRoomNameValid(createCinemaRoomDto.getName())){
            errors.put("Cinema room name is invalid:", "It must contain one uppercase and no more than 50 lowercase " + createCinemaRoomDto.getName());
        }
        if(!isCinemaRoomRowValid(createCinemaRoomDto.getRowQuantity())){
            errors.put("Cinema room row number is invalid:", "It must be int and be greater than 0 " + createCinemaRoomDto.getRowQuantity());
        }
        if(!isCinemaRoomPlaceValid(createCinemaRoomDto.getPlaceQuantity())){
            errors.put("Cinema room place number is invalid:", "It must be int and be greater than 0 " + createCinemaRoomDto.getPlaceQuantity());
        }
        if(!isCinemaIdValid(createCinemaRoomDto.getCinemaId())){
            errors.put("Cinema room cinema id is invalid:", "It cannot be null and must be grater than 0 " + createCinemaRoomDto.getCinemaId());
        }

        return errors;
    }

    private boolean isCinemaRoomNameValid(String name){
        return name != null && name.matches("[A-Za-z\\d][A-Za-z\\d\\s]{0,50}");
    }

    private boolean isCinemaRoomRowValid(int row){
        return row > 0;
    }

    private boolean isCinemaRoomPlaceValid(int place){
        return place > 0;
    }

    private boolean isCinemaIdValid(Long cinemaId){
        return cinemaId != null && cinemaId > 0;
    }
}
