package ogorek.wojciech.domain.model.movie.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateMovieDtoValidatorTest {

    @Test
    @DisplayName("when create movie dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateMovieDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Create movie dto object is invalid: It is null");
    }

    @Test
    @DisplayName("when create movie dto title is invalid")
    public void test2(){

        var title = "movie";
        var genre = "Genre";
        var startDate = LocalDateTime.of(2021, 12, 21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12, 21, 4, 30);

        var createMovieDto = CreateMovieDto
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateMovieDtoValidator(), createMovieDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Create movie dto title is invalid:");
        
    }

    @Test
    @DisplayName("when create movie dto genre is invalid")
    public void test3(){

        var title = "Movie";
        var genre = "genre";
        var startDate = LocalDateTime.of(2021, 12, 21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12, 21, 4, 30);

        var createMovieDto = CreateMovieDto
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateMovieDtoValidator(), createMovieDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Create movie dto genre is invalid:");

    }

    @Test
    @DisplayName("when create movie dto start date is invalid")
    public void test4(){

        var title = "Movie";
        var genre = "genre";
        LocalDateTime startDate = null;
        var endDate = LocalDateTime.of(2022, 12, 21, 4, 30);

        var createMovieDto = CreateMovieDto
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateMovieDtoValidator(), createMovieDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageContaining("Create movie dto start date is is invalid:");

    }

    @Test
    @DisplayName("when create movie dto end date is invalid")
    public void test5(){

        var title = "Movie";
        var genre = "genre";
        var startDate = LocalDateTime.of(2022, 12, 21, 4, 30);
        LocalDateTime endDate = null;

        var createMovieDto = CreateMovieDto
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateMovieDtoValidator(), createMovieDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageContaining("Create movie dto end date is is invalid:");

    }

}
