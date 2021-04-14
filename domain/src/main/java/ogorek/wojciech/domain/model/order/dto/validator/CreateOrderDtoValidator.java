package ogorek.wojciech.domain.model.order.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.ticket.enums.State;

import java.util.*;

public class CreateOrderDtoValidator implements Validator<CreateOrderDto> {

    @Override
    public Map<String, String> validate(CreateOrderDto createOrderDto) {
        var errors = new HashMap<String,String>();

        if(Objects.isNull(createOrderDto)){
            errors.put("Create order dto object is invalid:", "It is null");
            return errors;
        }
        if(!isOrderUsernameValid(createOrderDto.getUsername())){
            errors.put("Create order dto username is invalid:", "It must begin with uppercase and length cannot be grater than 20 - " + createOrderDto.getUsername());
        }
        if(!invalidOrderSeanceId(createOrderDto.getSeanceId())){
            errors.put("Create order dto seance id is invalid:", "It cannot be null and must be grater than 0 - " + createOrderDto.getSeanceId());
        }
        if(!isOrderSeatIdsValid(createOrderDto.getSeatIds())){
            errors.put("Create order dto seat id is invalid:", "It cannot be null and must be grater than 0 - " + createOrderDto.getSeatIds());
        }
        if(!isOccupancySizeValid(createOrderDto.getOccupancies().size(),createOrderDto.getSeatIds().size())){
            errors.put("Create order dto occupancies and seat id size is invalid:", "There must be one occupancy for every seat id - ");
        }
        if(!invalidOrderOccupancies(createOrderDto.getOccupancies())){
            errors.put("Create order dto occupancies is invalid:", "It must be one of this: FAMILY,GROUP,MINOR,SENIOR,STUDENT - " + createOrderDto.getOccupancies());
        }
        if(!invalidOrderState(createOrderDto.getState())){
            errors.put("Create order dto state is invalid:", "It must be one of this: BOUGHT, FREE, RESERVED - " + createOrderDto.getState());
        }

        return errors;
    }

    private boolean isOrderUsernameValid(String username){
        return username != null && username.matches("[A-Z][a-z\\d]{0,20}");
    }

    private boolean invalidOrderSeanceId(Long id){
        return id != null && id > 0;
    }

    private boolean isOrderSeatIdsValid(List<Long> ids){
        return ids != null && ids.stream().allMatch(element -> element > 0);
    }
    private boolean isOccupancySizeValid(int occupancySize, int seatSize){
        return occupancySize == seatSize;
    }

    private boolean invalidOrderOccupancies(List<Occupancy> occupancies) {
        return Arrays.asList(Occupancy.values()).containsAll(occupancies);
    }

    private boolean invalidOrderState(State state){
        return Arrays.asList(State.values()).contains(state);
    }
}
