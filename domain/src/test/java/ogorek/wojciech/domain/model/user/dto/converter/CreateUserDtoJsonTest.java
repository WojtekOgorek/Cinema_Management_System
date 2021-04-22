package ogorek.wojciech.domain.model.user.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.user.dto.CreateUserDto;
import ogorek.wojciech.domain.model.user.enums.Role;
import ogorek.wojciech.extension.user.dto.converter.CreateUserDtoJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(CreateUserDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateUserDtoJsonTest {

    private final CreateUserDtoJsonConverter createUserDtoJsonConverter;

    @Test
    @DisplayName("when create user dto conversion from json is correct")
    public void test1(){

        var name = "Name";
        var surname = "Surname";
        var username = "Username";
        var password = "woj7eczeK%";
        var passwordConfirmation = "woj7eczeK%";
        var email = "abc@abc.com";
        var role = Role.USER;

        var expectedUser = List.of(CreateUserDto
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .passwordConfirmation(passwordConfirmation)
                .email(email)
                .role(role)
                .build());

        var userFromJson = createUserDtoJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(userFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedUser);
    }
}
