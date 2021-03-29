package ogorek.wojciech.domain.configs.validator;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public interface Validator<T> {
    Map<String, String> validate(T t);

    static <T> void validate(Validator<T> validator, T item){
        var errors = validator.validate(item);
        if(!errors.isEmpty()){
            var message =
                    errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " " + e.getValue())
                    .collect(Collectors.joining(","));
            throw new AppValidationException("Validation errors: " + message);
        }
    }
}
