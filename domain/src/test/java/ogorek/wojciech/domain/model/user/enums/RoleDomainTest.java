package ogorek.wojciech.domain.model.user.enums;

import ogorek.wojciech.domain.model.ticket.enums.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleDomainTest {

    @Test
    @DisplayName("when role has all necessary properties")
    public void test1(){
        var roleValues = Arrays.stream(Role.values()).map(Objects::toString).collect(Collectors.toList());

        assertThat(roleValues).containsExactly("ADMIN", "USER");
    }
}
