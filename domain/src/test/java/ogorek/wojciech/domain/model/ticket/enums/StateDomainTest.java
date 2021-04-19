package ogorek.wojciech.domain.model.ticket.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StateDomainTest {


    @ParameterizedTest
    @EnumSource(value = State.class)
    @DisplayName("when state case format and length is correct")
    public void test1(State state){
        assertThat(state.toString()).matches("[A-Z]{1,10}");
    }

    @Test
    @DisplayName("when state has all necessary properties")
    public void test2(){
        var stateValues = Arrays.stream(State.values()).map(Objects::toString).collect(Collectors.toList());

        assertThat(stateValues).containsExactly("BOUGHT", "FREE", "RESERVED");
    }

}
