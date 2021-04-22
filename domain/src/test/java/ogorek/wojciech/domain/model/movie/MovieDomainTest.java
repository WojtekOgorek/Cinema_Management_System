package ogorek.wojciech.domain.model.movie;

import ogorek.wojciech.domain.model.movie.dto.GetMovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieDomainTest {


    @Test
    @DisplayName("when conversion to get movie dto is correct")
    public void test1(){

        var id = 1L;
        var title = "Movie";
        var genre = "Genre";
        var startDate = LocalDateTime.of(2021, 12,21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12,21, 4, 30);

        var movie = Movie
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        var expectedMovie = GetMovieDto
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        var getMovieDto = movie.toGetMovieDto();

        assertThat(getMovieDto).isEqualTo(expectedMovie);
    }

    @Test
    @DisplayName("when movie has all necessary properties")
    public void test2(){

        var id = 1L;
        var title = "Movie";
        var genre = "Genre";
        var startDate = LocalDateTime.of(2021, 12,21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12,21, 4, 30);

        var movie = Movie
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThat(movie).hasFieldOrProperty("id");
        assertThat(movie).hasFieldOrProperty("title");
        assertThat(movie).hasFieldOrProperty("genre");
        assertThat(movie).hasFieldOrProperty("startDate");
        assertThat(movie).hasFieldOrProperty("endDate");
    }

    @Test
    @DisplayName("when movie properties are correct")
    public void test3(){

        var id = 1L;
        var title = "Movie";
        var genre = "Genre";
        var startDate = LocalDateTime.of(2021, 12,21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12,21, 4, 30);

        var movie = Movie
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThat(movie.id).isGreaterThan(0);
        assertThat(movie.title).matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
        assertThat(movie.genre).matches("[A-Z][a-z]{0,20}( [A-Z][a-z]{0,20})?");
        assertThat(movie.startDate).isInstanceOf(LocalDateTime.class);
        assertThat(movie.endDate).isInstanceOf(LocalDateTime.class);
    }

    @Test
    @DisplayName("when movie is not null")
    public void test4(){
        var id = 1L;
        var title = "Movie";
        var genre = "Genre";
        var startDate = LocalDateTime.of(2021, 12,21, 4, 30);
        var endDate = LocalDateTime.of(2022, 12,21, 4, 30);

        var movie = Movie
                .builder()
                .id(id)
                .title(title)
                .genre(genre)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assertThat(movie).isNotEqualTo(null);
    }
}
