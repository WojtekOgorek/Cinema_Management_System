package ogorek.wojciech.domain.model.user.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.domain.model.user.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateUserDtoValidatorTest {

    @Test
    @DisplayName("when create user dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), null ))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Create user dto object is invalid:");
    }

    @Test
    @DisplayName("when create user dto name is invalid")
    public void test2(){

        var name = "name";
        var surname = "Surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto name is invalid:");
    }

    @Test
    @DisplayName("when create user dto surname is invalid")
    public void test3(){

        var name = "Name";
        var surname = "surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto surname is invalid:");
    }

    @Test
    @DisplayName("when create user dto username is invalid")
    public void test4(){

        var name = "Name";
        var surname = "Surname";
        var username = "us";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto username is invalid:");
    }

    @Test
    @DisplayName("when create user dto password is invalid")
    public void test5(){

        var name = "Name";
        var surname = "Surname";
        var username = "username";
        var password = "woj7ecze%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto password is invalid:");
    }

    @Test
    @DisplayName("when create user dto password confirmation is invalid")
    public void test6(){

        var name = "Name";
        var surname = "Surname";
        var username = "username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczek%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto password confirmation is invalid:");
    }

    @Test
    @DisplayName("when create user dto email is invalid")
    public void test7(){

        var name = "Name";
        var surname = "Surname";
        var username = "username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc.abc.com";
        var role = Role.USER;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto email is invalid:");
    }

    @Test
    @DisplayName("when create user dto role is invalid")
    public void test8(){

        var name = "Name";
        var surname = "Surname";
        var username = "username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        Role role = null;

        var createUserDto = CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUserDtoValidator(), createUserDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create user dto role is invalid:");
    }
}
