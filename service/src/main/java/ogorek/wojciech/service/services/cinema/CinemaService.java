package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema.Cinema;
import ogorek.wojciech.domain.model.cinema.dto.*;
import ogorek.wojciech.domain.model.cinema.dto.validator.CreateCinemaDtoValidator;
import ogorek.wojciech.domain.model.cinema.repository.CinemaRepository;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithCinemaRooms;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithMovies;
import ogorek.wojciech.domain.model.cinema.views.CinemaWithSeances;
import ogorek.wojciech.domain.model.city.repository.CityRepository;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CityRepository cityRepository;

    public List<GetCinemaDto> findAllCinemas() {
        return cinemaRepository
                .findAll()
                .stream()
                .map(Cinema::toGetCinemaDto)
                .collect(Collectors.toList());

    }

    public GetCinemaDto findCinemaById(Long id) {
        return cinemaRepository
                .findById(id)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow();
    }

    public GetCinemaDto addCinema(CreateCinemaDto createCinemaDto) {
        Validator.validate(new CreateCinemaDtoValidator(), createCinemaDto);
        if(cityRepository.findById(createCinemaDto.getCityId()).isEmpty()){
            throw new AppServiceException("There is no such city id in Db - fail adding cinema");
        }
        var cinema = createCinemaDto.toCinema();
        return cinemaRepository
                .add(cinema)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow();
    }

    public GetCinemaDto updateCinema(Long id, CreateCinemaDto createCinemaDto) {
        Validator.validate(new CreateCinemaDtoValidator(), createCinemaDto);
        if(cityRepository.findById(createCinemaDto.getCityId()).isEmpty()){
            throw new AppServiceException("There is no such city id in Db - fail adding cinema");
        }

        var cinemaToUpdate = Cinema
                .builder()
                .id(id)
                .name(createCinemaDto.getName())
                .cityId(createCinemaDto.getCityId())
                .build();


        return cinemaRepository
                .update(cinemaToUpdate)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow();
    }

    public GetCinemaDto deleteCinema(Long id) {
        return cinemaRepository
                .delete(id)
                .map(Cinema::toGetCinemaDto)
                .orElseThrow();
    }

    public boolean deleteAllCinemas() {
        return cinemaRepository
                .deleteAll();
    }

    public List<GetCinemaWithCinemaRoomsDto> findCinemasWithCinemaRooms() {
        return cinemaRepository
                .cinemasWithCinemaRooms()
                .stream()
                .map(CinemaWithCinemaRooms::toGetCinemaWithCinemaRoomsDto)
                .collect(Collectors.toList());
    }

    public List<GetCinemaWithCinemaRoomsDto> findOneCinemaWithCinemaRooms(Long id) {
        return cinemaRepository
                .specificCinemaWithCinemaRooms(id)
                .stream()
                .map(CinemaWithCinemaRooms::toGetCinemaWithCinemaRoomsDto)
                .collect(Collectors.toList());
    }

    public List<GetCinemaWithMoviesDto> findCinemasWithMovies() {
        return cinemaRepository
                .cinemasWithMovies()
                .stream()
                .map(CinemaWithMovies::getCinemaWithMoviesDto)
                .collect(Collectors.toList());
    }

    public List<GetCinemaWithMoviesDto> findOneCinemaWithMovies(Long id) {
        return cinemaRepository
                .specificCinemaWithMovies(id)
                .stream()
                .map(CinemaWithMovies::getCinemaWithMoviesDto)
                .collect(Collectors.toList());
    }

    public List<GetCinemaWithSeancesDto> findOneCinemaWithSeances(Long id) {
        return cinemaRepository
                .specificCinemaWithSeances(id)
                .stream()
                .map(CinemaWithSeances::getCinemaWithSeancesDto)
                .collect(Collectors.toList());
    }
}
