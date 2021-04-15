package ogorek.wojciech.domain.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Role role;

    public User toUser(){
        return User
                .builder()
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

}
