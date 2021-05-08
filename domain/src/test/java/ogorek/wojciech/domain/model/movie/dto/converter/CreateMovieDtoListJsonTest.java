package ogorek.wojciech.domain.model.movie.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
import ogorek.wojciech.extension.movie.dto.converter.CreateMovieDtoListJsonExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@ExtendWith(CreateMovieDtoListJsonExtension.class)
@RequiredArgsConstructor
public class CreateMovieDtoListJsonTest {

    private final CreateMovieDtoListJsonConverter createMovieDtoListJsonConverter;

    @Test
    @DisplayName("when create movie dto list json conversion works properly")
    public void test1() {

        var expectedMovie = List.of(
                CreateMovieDto
                        .builder()
                        .title("Title")
                        .genre("Genre")
                        .startDate(LocalDateTime.of(2021, 12, 20, 12, 15))
                        .endDate(LocalDateTime.of(2022, 12, 20, 12, 15))
                        .build());

        var moviesFromJson = createMovieDtoListJsonConverter.fromJson().orElseThrow();

        assertDoesNotThrow(() -> assertThat(moviesFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedMovie);

    }
}
