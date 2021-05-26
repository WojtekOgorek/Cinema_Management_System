package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema_room.repository.CinemaRoomRepository;
import ogorek.wojciech.domain.model.movie.repository.MovieRepository;
import ogorek.wojciech.domain.model.seance.Seance;
import ogorek.wojciech.domain.model.seance.dto.CreateSeanceDto;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceByDateDto;
import ogorek.wojciech.domain.model.seance.dto.GetSeanceDto;
import ogorek.wojciech.domain.model.seance.dto.validator.CreateSeanceDtoValidator;
import ogorek.wojciech.domain.model.seance.repository.SeanceRepository;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.domain.model.ticket.enums.State;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final MovieRepository movieRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    public List<GetSeanceDto> findAllSeances() {
        return seanceRepository
                .findAll()
                .stream()
                .map(Seance::toGetSeanceDto)
                .collect(Collectors.toList());
    }

    public GetSeanceDto findSeanceById(Long id) {
        return seanceRepository
                .findById(id)
                .map(Seance::toGetSeanceDto)
                .orElseThrow();
    }

    public GetSeanceDto addSeance(CreateSeanceDto createSeanceDto) {
        Validator.validate(new CreateSeanceDtoValidator(), createSeanceDto);
        if (movieRepository.findById(createSeanceDto.getMovieId()).isEmpty()) {
            throw new AppServiceException("Fail to add seance. There is no such movie id in Db " + createSeanceDto.getMovieId());
        } else if (cinemaRoomRepository.findById(createSeanceDto.getCinemaRoomId()).isEmpty()) {
            throw new AppServiceException("Fail to add seance. There is no such cinemaRoom id in Db " + createSeanceDto.getCinemaRoomId());
        }
        var seance = createSeanceDto.toSeance();
        return seanceRepository
                .add(seance)
                .map(Seance::toGetSeanceDto)
                .orElseThrow();
    }

    public GetSeanceDto updateSeance(Long id, CreateSeanceDto createSeanceDto) {
        Validator.validate(new CreateSeanceDtoValidator(), createSeanceDto);
        if (movieRepository.findById(createSeanceDto.getMovieId()).isEmpty()) {
            throw new AppServiceException("Fail to update seance. There is no such movie id in Db " + createSeanceDto.getMovieId());
        } else if (cinemaRoomRepository.findById(createSeanceDto.getCinemaRoomId()).isEmpty()) {
            throw new AppServiceException("Fail to update seance. There is no such cinemaRoom id in Db " + createSeanceDto.getCinemaRoomId());
        }
        var seanceToUpdate = Seance
                .builder()
                .id(id)
                .cinemaRoomId(createSeanceDto.getCinemaRoomId())
                .dateTime(createSeanceDto.getDateTime())
                .movieId(createSeanceDto.getMovieId())
                .build();

        return seanceRepository
                .update(seanceToUpdate)
                .map(Seance::toGetSeanceDto)
                .orElseThrow();
    }

    public GetSeanceDto deleteSeance(Long id) {
        return seanceRepository
                .delete(id)
                .map(Seance::toGetSeanceDto)
                .orElseThrow();
    }

    public boolean deleteAllSeances() {
        return seanceRepository
                .deleteAll();
    }

    public List<GetSeanceByDateDto> findSeancesByDate(String from, String to, String state) {

        return state.toUpperCase().equals(String.valueOf(State.RESERVED)) ?
                seanceRepository
                        .getSeanceByDateTicketsReserved(from, to)
                        .stream()
                        .map(SeanceByDate::toGetSeanceByDateDto)
                        .collect(Collectors.toList())
                :
                seanceRepository
                        .getSeanceByDateTicketsBought(from, to)
                        .stream()
                        .map(SeanceByDate::toGetSeanceByDateDto)
                        .collect(Collectors.toList());
    }

}
