package ogorek.wojciech.domain.model.user;

import lombok.*;
import ogorek.wojciech.domain.model.user.dto.GetUserDto;
import ogorek.wojciech.domain.model.user.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {
    Long id;
    String name;
    String surname;
    String username;
    String password;
    String email;
    Role role;

    public GetUserDto toGetUserDto(){
        return GetUserDto
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .username(username)
                .role(role)
                .build();
    }

    public void setPassword(PasswordEncoder passwordEncoder){
        passwordEncoder.encode(password);
    }

    public User withChangedPassword(String changedPassword){
        //todo check confirmation
        return User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .password(changedPassword)
                .role(role)
                .build();
    }
}
