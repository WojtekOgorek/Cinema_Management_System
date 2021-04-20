package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema.repository.CinemaRepository;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import ogorek.wojciech.domain.model.cinema_room.dto.CreateCinemaRoomDto;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomDto;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeanceDto;
import ogorek.wojciech.domain.model.cinema_room.dto.GetCinemaRoomWithSeatsDto;
import ogorek.wojciech.domain.model.cinema_room.dto.validator.CreateCinemaRoomDtoValidator;
import ogorek.wojciech.domain.model.cinema_room.repository.CinemaRoomRepository;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaRoomService {
    private final CinemaRoomRepository cinemaRoomRepository;
    private final CinemaRepository cinemaRepository;

    public List<GetCinemaRoomDto> findAllCinemaRooms() {
        return cinemaRoomRepository
                .findAll()
                .stream()
                .map(CinemaRoom::toGetCinemaRoomDto)
                .collect(Collectors.toList());
    }

    public GetCinemaRoomDto findCinemaRoomById(Long id) {
        return cinemaRoomRepository
                .findById(id)
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow();
    }


    public GetCinemaRoomDto addCinemaRoom(CreateCinemaRoomDto createCinemaRoomDto) {
        Validator.validate(new CreateCinemaRoomDtoValidator(), createCinemaRoomDto);
        if(cinemaRepository.findById(createCinemaRoomDto.getCinemaId()).isEmpty()){
            throw new AppServiceException("Fail to add cinemaRoom, there is no such cinema in db");
        }
        var cinemaRoom = createCinemaRoomDto.toCinemaRoom();
        return cinemaRoomRepository
                .add(cinemaRoom)
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow();
    }

    public GetCinemaRoomDto updateCinemaRoom(CreateCinemaRoomDto createCinemaRoomDto) {
        Validator.validate(new CreateCinemaRoomDtoValidator(), createCinemaRoomDto);
        if(cinemaRepository.findById(createCinemaRoomDto.getCinemaId()).isEmpty()){
            throw new AppServiceException("Fail to update cinemaRoom, there is no such cinema in db");
        }
        var cinemaRoom = createCinemaRoomDto.toCinemaRoom();
        return cinemaRoomRepository
                .update(cinemaRoom)
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow();
    }

    public GetCinemaRoomDto deleteCinemaRoom(Long id) {
        return cinemaRoomRepository
                .delete(id)
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow();
    }

    public boolean deleteAllCinemaRooms() {
        return cinemaRoomRepository
                .deleteAll();
    }

    public List<GetCinemaRoomWithSeanceDto> findOneCinemaRoomWithSeances(Long id) {
        return cinemaRoomRepository
                .specificCinemaRoomWithSeances(id)
                .stream()
                .map(CinemaRoomWithSeance::toGetCinemaRoomWithSeanceDto)
                .collect(Collectors.toList());
    }

    public List<GetCinemaRoomWithSeatsDto> findOneCinemaRoomWithSeats(Long id) {
        return cinemaRoomRepository
                .specificCinemaRoomWithSeats(id)
                .stream()
                .map(CinemaRoomWithSeats::toCinemaRoomWithSeatsDto)
                .collect(Collectors.toList());
    }


}
