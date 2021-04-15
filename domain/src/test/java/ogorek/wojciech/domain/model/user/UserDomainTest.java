package ogorek.wojciech.domain.model.user;

import ogorek.wojciech.domain.model.user.dto.GetUserDto;
import ogorek.wojciech.domain.model.user.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserDomainTest {

    @Test
    @DisplayName("when conversion to get user is correct")
    public void test1(){

        var id = 1L;
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var email = "abc@abc.com";
        var role = Role.USER;

        var user = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .role(role)
                .build();

        var expectedUser = GetUserDto
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .role(role)
                .build();

        var getUserDto = user.toGetUserDto();

        assertThat(getUserDto).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("when user's password has been changed correctly")
    public void test2(){

        var id = 1L;
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var email = "abc@abc.com";
        var role = Role.USER;

        var password = "woj7eczeK%";
        var newPassword = "7adeuszeK#";

        var user = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();

        var expectedUser = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(newPassword)
                .email(email)
                .role(role)
                .build();

        var userWithChangedPassword = user.withChangedPassword(newPassword);

        assertThat(userWithChangedPassword).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("when user has all necessary properties")
    public void test3(){
        var id = 1L;
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var user = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();

        assertThat(user).hasFieldOrProperty("id");
        assertThat(user).hasFieldOrProperty("name");
        assertThat(user).hasFieldOrProperty("surname");
        assertThat(user).hasFieldOrProperty("username");
        assertThat(user).hasFieldOrProperty("password");
        assertThat(user).hasFieldOrProperty("email");
        assertThat(user).hasFieldOrProperty("role");
    }

    @Test
    @DisplayName("when user properties are correct")
    public void test4(){
        var id = 1L;
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var user = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();

        assertThat(user.id).isNotEqualTo(null).isGreaterThan(0);
        assertThat(user.name).isNotEqualTo(null).matches("[A-Z][A-Za-z\\d\\s]{2,50}");
        assertThat(user.surname).isNotEqualTo(null).matches("[A-Z][A-Za-z\\d\\s]{2,50}");
        assertThat(user.username).isNotEqualTo(null).matches("[A-Z]?[A-Za-z\\d\s]{3,50}");
        assertThat(user.password).isNotEqualTo(null).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        assertThat(user.email).isNotEqualTo(null).matches("[a-z\\d.]+@[a-z\\d.]+.[a-z]{2,3}");
        assertThat(user.role).isNotEqualTo(null).isInstanceOf(Role.class);

    }

    @Test
    @DisplayName("when user is not null")
    public void test5(){
        var id = 1L;
        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var user = User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();

        assertThat(user).isNotEqualTo(null);
    }
}
