package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.movie.Movie;
import ogorek.wojciech.domain.model.movie.dto.CreateMovieDto;
import ogorek.wojciech.domain.model.movie.dto.GetMovieDto;
import ogorek.wojciech.domain.model.movie.dto.validator.CreateMovieDtoValidator;
import ogorek.wojciech.domain.model.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<GetMovieDto> findAllMovies(){
        return movieRepository
                .findAll()
                .stream()
                .map(Movie::toGetMovieDto)
                .collect(Collectors.toList());
    }

    public GetMovieDto findMovieById(Long id){
        return movieRepository
                .findById(id)
                .map(Movie::toGetMovieDto)
                .orElseThrow();
    }

    public GetMovieDto addMovie(CreateMovieDto createMovieDto){
        Validator.validate(new CreateMovieDtoValidator(), createMovieDto);
        var movie = createMovieDto.toMovie();
        return movieRepository
                .add(movie)
                .map(Movie::toGetMovieDto)
                .orElseThrow();
    }

    public GetMovieDto updateMovie(CreateMovieDto createMovieDto){
        Validator.validate(new CreateMovieDtoValidator(), createMovieDto);
        var movie = createMovieDto.toMovie();
        return movieRepository
                .update(movie)
                .map(Movie::toGetMovieDto)
                .orElseThrow();
    }

    public GetMovieDto deleteMovie(Long id){
        return movieRepository
                .delete(id)
                .map(Movie::toGetMovieDto)
                .orElseThrow();
    }

    public boolean deleteAllMovies(){
        return movieRepository
                .deleteAll();
    }

    public GetMovieDto findMovieByName(String name){
        return movieRepository
                .getMovieByName(name)
                .map(Movie::toGetMovieDto)
                .orElseThrow();
    }

    public List<GetMovieDto> findMoviesByGenre(String genre){
        return movieRepository
                .getMoviesByGenre(genre)
                .stream()
                .map(Movie::toGetMovieDto)
                .collect(Collectors.toList());
    }

    public List<GetMovieDto> findMoviesByEmissionDate(String from, String to){
        return movieRepository
                .getMoviesByEmissionDate(from, to)
                .stream()
                .map(Movie::toGetMovieDto)
                .collect(Collectors.toList());
    }

    public List<GetMovieDto> findMoviesByFirstLetters(String letters){
        return movieRepository
                .getMoviesByFirstLetters(letters)
                .stream()
                .map(Movie::toGetMovieDto)
                .collect(Collectors.toList());
    }
}
