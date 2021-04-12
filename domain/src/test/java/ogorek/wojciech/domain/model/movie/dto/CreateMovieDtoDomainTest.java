package ogorek.wojciech.domain.model.movie.dto;

import ogorek.wojciech.domain.model.movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateMovieDtoDomainTest {
    @Test
    @DisplayName("when creating movie is correct")
    public void test1() {

        var title = "Movie";
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

        var expectedMovie = Movie
                .builder()
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        var movie = createMovieDto.toMovie();

        assertThat(movie).isEqualTo(expectedMovie);
    }


    @Test
    @DisplayName("when create movie dto has correct properties")
    public void test2() {

        var title = "Movie";
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

        assertThat(createMovieDto).hasOnlyFields("title", "genre", "startDate", "endDate");
    }


}
