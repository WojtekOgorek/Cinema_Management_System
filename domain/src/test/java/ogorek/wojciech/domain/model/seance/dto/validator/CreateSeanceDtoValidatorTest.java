package ogorek.wojciech.domain.model.seance.dto.validator;

import ogorek.wojciech.domain.configs.validator.AppValidationException;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateSeanceDtoValidatorTest {

    @Test
    @DisplayName("when create seance dto is null")
    public void test1(){

        assertThatThrownBy(() -> Validator.validate(new CreateSeanceDtoValidator(), null))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]: ")
                .hasMessageContaining("Seance object is invalid:");

    }

    @Test
    @DisplayName("when create seance dto date is invalid")
    public void test2(){
        var movieId = 1L;
        var cinemaRoomId = 2L;
        LocalDateTime dateTime = null;

        var createSeanceDto = CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeanceDtoValidator(), createSeanceDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create seance dto date is invalid:");
    }

    @Test
    @DisplayName("when create seance dto movie id is invalid")
    public void test3(){
        var movieId = 0L;
        var cinemaRoomId = 2L;
        LocalDateTime dateTime = LocalDateTime.of(2021,1,1,12,15,15);

        var createSeanceDto = CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeanceDtoValidator(), createSeanceDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create seance dto invalid number for movie:");
    }

    @Test
    @DisplayName("when create seance dto cinema room id is invalid")
    public void test4(){
        var movieId = 1L;
        var cinemaRoomId = 0L;
        LocalDateTime dateTime = LocalDateTime.of(2021,1,1,12,15,15);

        var createSeanceDto = CreateSeanceDto
                .builder()
                .movieId(movieId)
                .cinemaRoomId(cinemaRoomId)
                .dateTime(dateTime)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateSeanceDtoValidator(), createSeanceDto))
                .isInstanceOf(AppValidationException.class)
                .hasMessageStartingWith("[VALIDATOR ERROR]:")
                .hasMessageContaining("Create seance dto invalid number for cinema room:");
    }
}
