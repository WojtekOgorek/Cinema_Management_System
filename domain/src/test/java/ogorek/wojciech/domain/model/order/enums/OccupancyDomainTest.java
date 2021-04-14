package ogorek.wojciech.domain.model.order.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class OccupancyDomainTest {

    @Test
    @DisplayName("when occupancy has all necessary properties")
    public void test1(){

        var ocuupancies = Arrays
                .stream(Occupancy.values())
                .map(Objects::toString)
                .collect(Collectors.toList());

        assertThat(ocuupancies).containsExactly("FAMILY","GROUP","MINOR","SENIOR","STUDENT");
    }
}
