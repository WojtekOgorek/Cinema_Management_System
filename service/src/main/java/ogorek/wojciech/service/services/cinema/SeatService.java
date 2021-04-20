package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import ogorek.wojciech.domain.model.cinema_room.repository.CinemaRoomRepository;
import ogorek.wojciech.domain.model.seance.repository.SeanceRepository;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.domain.model.seat.Seat;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import ogorek.wojciech.domain.model.seat.dto.GetSeatDto;
import ogorek.wojciech.domain.model.seat.dto.GetSeatWithState;
import ogorek.wojciech.domain.model.seat.dto.validator.CreateSeatDtoValidator;
import ogorek.wojciech.domain.model.seat.repository.SeatRepository;
import ogorek.wojciech.domain.model.ticket.enums.State;
import ogorek.wojciech.domain.model.ticket.repository.TicketRepository;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeanceRepository seanceRepository;
    private final TicketRepository ticketRepository;


    public GetSeatDto findSeatById(Long id) {
        return seatRepository
                .findById(id)
                .map(Seat::toGetSeatDto)
                .orElseThrow();
    }

    public GetSeatDto addSeat(CreateSeatDto createSeatDto) {
        Validator.validate(new CreateSeatDtoValidator(), createSeatDto);
        var getCinemaRoomDto = cinemaRoomRepository
                .findById(createSeatDto.getCinemaRoomId())
                .map(CinemaRoom::toGetCinemaRoomDto)
                .orElseThrow(() -> new AppServiceException("Fail adding seat. No such cinemaRoom Id in Db"));

        if (createSeatDto.getSeatPlace() > getCinemaRoomDto.getPlaceQuantity()) {
            throw new AppServiceException("""
                    Fail adding seat. 
                    Seat place must be available 
                    in cinemaRoom = """ + getCinemaRoomDto.getPlaceQuantity() +
                    "but seat place = " + createSeatDto.getSeatPlace());

        } else if (createSeatDto.getSeatRow() > getCinemaRoomDto.getRowQuantity()) {
            throw new AppServiceException("""
                    Fail adding seat. 
                    Seat row must be available 
                    in cinemaRoom = """ + getCinemaRoomDto.getRowQuantity() +
                    "but seat place = " + createSeatDto.getSeatRow());
        }


        var seatToAdd = createSeatDto.toSeat();
        return seatRepository
                .add(seatToAdd)
                .map(Seat::toGetSeatDto)
                .orElseThrow();

    }

    public GetSeatDto deleteSeat(Long id) {
        return seatRepository
                .delete(id)
                .map(Seat::toGetSeatDto)
                .orElseThrow();
    }

    public Runnable seatReservationChecker() {
        return () -> {
            try {
                seanceRepository
                        .getSeanceByDate(
                                String.valueOf(LocalDateTime.now()),
                                String.valueOf(LocalDateTime.now().plusMinutes(15)))
                        .stream()
                        .map(SeanceByDate::toGetSeanceByDateDto)
                        .forEach(ticket -> {
                            System.out.println("Reservation has been canceled for ticket id: " + ticket.getTicketId());
                            ticketRepository.delete(ticket.getTicketId());
                        });
            } catch (Exception e) {
                e.printStackTrace();
                throw new AppServiceException("Seat Reservation Checker exception");
            }
        };
    }

    public Map<Long, State> getSeatState(List<Long> ids) {
        return ids
                .stream()
                .map(seatRepository::getSeatWithState)
                .map(mapper -> mapper
                        .orElseThrow(() -> new AppServiceException("Seat state mapper failed"))
                        .toGetSeatWithState())
                .collect(Collectors.toMap(GetSeatWithState::getSeatId, GetSeatWithState::getState));

    }

    public boolean isSeatFree(Long id) {
        return seatRepository
                .getSeatWithState(id)
                .map(state -> state.toGetSeatWithState().getState())
                .equals(Optional.of(State.FREE));
    }
}
