package ogorek.wojciech.service.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.user.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizedUserDto {
    private Long id;
    private String username;
    private Role role;
}
