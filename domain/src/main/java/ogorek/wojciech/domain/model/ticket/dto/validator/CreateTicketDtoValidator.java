package ogorek.wojciech.domain.model.ticket.dto.validator;

import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.domain.model.ticket.enums.State;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateTicketDtoValidator implements Validator<CreateTicketDto> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Override
    public Map<String, String> validate(CreateTicketDto createTicketDto) {
        var errors = new HashMap<String, String>();

        if(Objects.isNull(createTicketDto)){
            errors.put("Ticket object is invalid", " Object cannot be null");
        }

        if(!isUserIdValid(createTicketDto.getUserId())){
            errors.put("Invalid id number for user ", " Id cannot be null and must be greater than 0 " + createTicketDto.getUserId());
        }

        if(!isSeanceIdValid(createTicketDto.getSeanceId())){
            errors.put("Invalid id number for seance ", " Id cannot be null and must be greater than 0 " + createTicketDto.getSeanceId());
        }

        if(!isSeatIdValid(createTicketDto.getSeatId())){
            errors.put("Invalid id number for seat ", " Id cannot be null and must be greater than 0 " + createTicketDto.getSeatId());
        }

        if(!isTicketPriceValid(createTicketDto.getPrice())){
            errors.put("Ticket price is invalid. ", " Price must be greater or equal to 0 and cannot be null " + createTicketDto.getPrice());
        }

        if(!isTicketDiscountValid(createTicketDto.getDiscount())){
            errors.put("Ticket discount is invalid. ", " Discount must be greater or equal to 0 and cannot be null " + createTicketDto.getDiscount());
        }
        if(isTicketStateValid(createTicketDto.getState())){
            errors.put("Ticket state is invalid. ", " State must be greater or equal to 0 and cannot be null " + createTicketDto.getState());
        }

        return errors;
    }

    private boolean isUserIdValid(Long id){
        return id != null && id > 0;
    }

    private boolean isSeanceIdValid(Long id){
        return id != null && id > 0;
    }

    private boolean isSeatIdValid(Long id){
        return id != null && id > 0;
    }

    private boolean isTicketPriceValid(BigDecimal price){
        return price != null && price.compareTo(BigDecimal.ZERO) >= 0;
    }
    private boolean isTicketDiscountValid(BigDecimal discount){
        return discount.compareTo(BigDecimal.ZERO) > 0 && discount.compareTo(BigDecimal.ZERO) <= 1.0;
    }
    private boolean isTicketStateValid(State state) {
        return Arrays.asList(State.values()).contains(state);
    }
}
