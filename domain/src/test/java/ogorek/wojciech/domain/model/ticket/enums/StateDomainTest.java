package ogorek.wojciech.domain.model.ticket.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StateDomainTest {

    @Test
    @DisplayName("when state has all necessary properties")
    public void test1(){
        var stateValues = Arrays.stream(State.values()).map(Objects::toString).collect(Collectors.toList());

        assertThat(stateValues).containsExactly("BOUGHT", "FREE", "RESERVED");
    }
}
