package ogorek.wojciech.domain.model.user.dto.validator;


import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.domain.model.user.enums.Role;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreateUserDtoValidator implements Validator<CreateUserDto> {

    @Override
    public Map<String, String> validate(CreateUserDto createUserDto) {


        var errors = new HashMap<String, String>();

        if (Objects.isNull(createUserDto)) {
            errors.put("Create user dto object is invalid:", "It is null");
            return errors;
        }
        if (!isCreateUserDtoNameValid(createUserDto.getName())) {
            errors.put("Create user dto name is invalid:", "It cannot be null and must begin with uppercase - " + createUserDto.getName());
        }
        if (!isCreateUserDtoSurnameValid(createUserDto.getSurname())) {
            errors.put("Create user dto surname is invalid:", "It cannot be null and must begin with uppercase - " + createUserDto.getSurname());
        }
        if (!isCreateUserDtoUsernameValid(createUserDto.getUsername())) {
            errors.put("Create user dto username is invalid:", "It must be min 3 chars - " + createUserDto.getUsername());
        }
        if (!isCreateUserDtoPasswordValid(createUserDto.getPassword())) {
            errors.put("Create user dto password is invalid:",
                    """ 
                             It must have min 8 chars and contains ->  
                              1 small letter, 1 upper letter, 1 digit, 1 special (@#$%^&+=) 
                              and no white spaces  
                            """ + createUserDto.getPassword());
        }
        if (!isCreateUserDtoConfirmationValid(createUserDto.getPassword(), createUserDto.getPasswordConfirmation())) {
            errors.put("Create user dto password confirmation is invalid:", "password cannot differ from password confirmation" +
                    " - " + createUserDto.getPassword() + " does not equal = " + createUserDto.getPasswordConfirmation());
        }

        if (!isCreateUserDtoEmailValid(createUserDto.getEmail())) {
            errors.put("Create user dto email is invalid:", " email cannot be null and must contain valid characters - " + createUserDto.getEmail());
        }

        if (!isCreateUserDtoRoleValid(createUserDto.getRole())) {
            errors.put("Create user dto role is invalid:", "role cannot be null and must equal Admin or createUserDto - " + createUserDto.getRole());
        }
        return errors;
    }

    private boolean isCreateUserDtoNameValid(String name) {
        return name != null && name.matches("[A-Z][A-Za-z\\d\\s]{2,50}");
    }

    private boolean isCreateUserDtoSurnameValid(String surname) {
        return surname != null && surname.matches("[A-Z][A-Za-z\\d\s]{2,50}");
    }

    private boolean isCreateUserDtoUsernameValid(String username) {
        return username != null && username.matches("[A-Z]?[A-Za-z\\d\s]{3,50}");
    }

    private boolean isCreateUserDtoPasswordValid(String password) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    private boolean isCreateUserDtoConfirmationValid(String password, String confirmation) {
        return password.equals(confirmation);
    }

    private boolean isCreateUserDtoEmailValid(String email) {
        return email != null && email.matches("[a-z\\d.]+@[a-z\\d.]+.[a-z]{2,3}");
    }

    private boolean isCreateUserDtoRoleValid(Role role) {
        return role != null &&
                Arrays
                        .asList(Role.values())
                        .contains(role);
    }

}
