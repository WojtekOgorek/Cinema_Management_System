package ogorek.wojciech.domain.model.user.dto;

import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateUserDtoDomainTest {

    @Test
    @DisplayName("when creating user is correct")
    public void test1(){
        var name = "Name";
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

        var expectedUser = User
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();

        var toUser = createUserDto.toUser();

        assertThat(toUser).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("when create user dto has correct properties")
    public void test2(){
        var name = "Name";
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

        assertThat(createUserDto).hasFieldOrProperty("name");
        assertThat(createUserDto).hasFieldOrProperty("surname");
        assertThat(createUserDto).hasFieldOrProperty("username");
        assertThat(createUserDto).hasFieldOrProperty("password");
        assertThat(createUserDto).hasFieldOrProperty("passwordConfirmation");
        assertThat(createUserDto).hasFieldOrProperty("email");
        assertThat(createUserDto).hasFieldOrProperty("role");
    }

    @Test
    @DisplayName("when create user dto password confirmation is invalid")
    public void test3(){
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
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

        assertThat(createUserDto.getPassword()).isNotEqualTo(createUserDto.getPasswordConfirmation());
    }
}
