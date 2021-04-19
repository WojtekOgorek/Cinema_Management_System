package ogorek.wojciech.domain.model.user.enums;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleDomainTest {

    @ParameterizedTest
    @EnumSource(value = Role.class)
    @DisplayName("when role case format and length is correct")
    public void test1(Role role){
        assertThat(role.toString()).matches("[A-Z]{1,10}");
    }

    @Test
    @DisplayName("when role has all necessary properties")
    public void test2(){
        var roleValues = Arrays.stream(Role.values()).map(Objects::toString).collect(Collectors.toList());

        assertThat(roleValues).containsExactly("ADMIN", "USER");
    }

}
