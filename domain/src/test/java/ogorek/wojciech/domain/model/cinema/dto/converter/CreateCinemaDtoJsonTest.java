package ogorek.wojciech.domain.model.cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.extension.cinema.dto.converter.CreateCinemaDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(CreateCinemaDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoJsonTest {
    private final CreateCinemaDtoJsonConverter createCinemaDtoJsonConverter;

    @Test
    @DisplayName("when create cinema dto json converter works properly")
    public void test1(){


        var expectedCinema = CreateCinemaDto
        .builder()
        .name("Multikino")
        .cityId(1L)
        .build();

        var cinemasFromJson = createCinemaDtoJsonConverter.fromJson().orElseThrow();


        Assertions.assertDoesNotThrow(() -> assertThat(cinemasFromJson)
                .isEqualTo(expectedCinema));
    }
}
