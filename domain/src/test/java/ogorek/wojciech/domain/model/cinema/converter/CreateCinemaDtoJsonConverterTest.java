package ogorek.wojciech.domain.model.cinema.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.cinema.dto.CreateCinemaDto;
import ogorek.wojciech.domain.model.cinema.dto.converter.CreateCinemaDtoJsonConverter;
import ogorek.wojciech.extension.cinema.dto.converter.CreateCinemaDtoConverterExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(CreateCinemaDtoConverterExtension.class)
@RequiredArgsConstructor
public class CreateCinemaDtoJsonConverterTest {
    private final CreateCinemaDtoJsonConverter createCinemaDtoJsonConverter;

    @Test
    @DisplayName("when create cinema dto json converter works properly")
    public void test1(){


        var expectedCinema = List.of(CreateCinemaDto
        .builder()
        .name("Multikino")
        .cityId(1L)
        .build());

        var cinemasFromJson = createCinemaDtoJsonConverter.fromJson().orElseThrow();

        assertThat(cinemasFromJson).isEqualTo(expectedCinema);
    }
}
