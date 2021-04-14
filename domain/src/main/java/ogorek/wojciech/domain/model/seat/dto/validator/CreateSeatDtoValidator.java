package ogorek.wojciech.domain.model.seat.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateSeatDtoValidator implements Validator<CreateSeatDto> {

    @Override
    public Map<String, String> validate(CreateSeatDto createSeatDto) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(createSeatDto)) {
            errors.put("Seat object is invalid:", "It is null");
            return errors;
        }
        if (!isCinemaRoomIdValid(createSeatDto.getCinemaRoomId())) {
            errors.put("Seat cinema room id is invalid:", "Id cannot be null and must be greater than 0 - " + createSeatDto.getCinemaRoomId());
        }
        if (!isPlaceValid(createSeatDto.getSeatPlace())) {
            errors.put("Seat place is invalid:", "Place must be greater than 0 - " + createSeatDto.getSeatPlace());
        }

        if (!isRowValid(createSeatDto.getSeatRow())) {
            errors.put("Seat row is invalid:", "Row must be greater than 0 -" + createSeatDto.getSeatRow());
        }

        return errors;
    }


    private boolean isCinemaRoomIdValid(Long id) {
        return id != null && id > 0;
    }

    private boolean isPlaceValid(int place) {
        return place > 0;
    }

    private boolean isRowValid(int row) {
        return row > 0;
    }

}
