package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.UserFunctors;
import ogorek.wojciech.domain.model.user.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Role role;

    public User toUser(){
        return User
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
    //todo check it password tez
    public UserEntity fromUser(User user) {

    var toGetUserDto = user.toGetUserDto();
        //change with password confirmation

        return UserEntity
                .builder()
                .id(toGetUserDto.getId())
                .name(toGetUserDto.getName())
                .surname(toGetUserDto.getSurname())
                .username(toGetUserDto.getUsername())
                .password(UserFunctors.toPassword.apply(user))
                .email(toGetUserDto.getEmail())
                .role(toGetUserDto.getRole())
                .build();
    }
}
