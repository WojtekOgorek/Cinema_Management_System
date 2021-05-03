package ogorek.wojciech.domain.model.cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.extension.cinema.dto.converter.CreateCinemaDtoListJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(CreateCinemaDtoListJsonExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoListJsonTest {
    private final CreateCinemaDtoListJsonConverter createCinemaDtoListJsonConverter;

    @Test
    @DisplayName("when create cinema dto list json converter works properly")
    public void test1(){


        var expectedCinema = List.of(CreateCinemaDto
        .builder()
        .name("Multikino")
        .cityId(1L)
        .build());

        var cinemasFromJson = createCinemaDtoListJsonConverter.fromJson().orElseThrow();


        Assertions.assertDoesNotThrow(() -> assertThat(cinemasFromJson)
                .hasSize(1)
                .containsExactlyElementsOf(expectedCinema));
    }
}
