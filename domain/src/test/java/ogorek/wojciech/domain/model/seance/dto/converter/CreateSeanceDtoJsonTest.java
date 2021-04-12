package ogorek.wojciech.domain.model.seance.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;
import ogorek.wojciech.extension.seance.dto.converter.CreateSeanceDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(CreateSeanceDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateSeanceDtoJsonTest {

    private final CreateSeanceDtoJsonConverter createSeanceDtoJsonConverter;

    @Test
    @DisplayName("when create seance dto json converter works properly")
    public void test1() {

        var movieId = 1L;
        var cinemaRoomId = 2L;
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 1, 12, 15, 15);

        var expectedSeance = List.of(CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build());

        var createSeanceDtoFromJson = createSeanceDtoJsonConverter.fromJson().orElseThrow();

        Assertions.assertDoesNotThrow(() -> assertThat(createSeanceDtoFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedSeance);
    }
}
