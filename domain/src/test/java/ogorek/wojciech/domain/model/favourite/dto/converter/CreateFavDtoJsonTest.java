package ogorek.wojciech.domain.model.favourite.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.favourite.dto.CreateFavDto;
import ogorek.wojciech.extension.favourite.dto.converter.CreateFavDtoJsonExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(CreateFavDtoJsonExtension.class)
@RequiredArgsConstructor
public class CreateFavDtoJsonTest {

    private final CreateFavouriteDtoJsonConverter createFavouriteDtoJsonConverter;

    @Test
    @DisplayName("when create favourite dto json converter work properly")
    public void test1() {
        var userId = 1L;
        var movieId = 2L;

        var expectedFavDto = List.of(CreateFavDto
                .builder()
                .userId(userId)
                .movieId(movieId)
                .build());

        var favouriteFromJson = createFavouriteDtoJsonConverter.fromJson().orElseThrow();


        Assertions.assertDoesNotThrow(() -> assertThat(favouriteFromJson))
                .hasSize(1)
                .containsExactlyElementsOf(expectedFavDto);
    }
}
