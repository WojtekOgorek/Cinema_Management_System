package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.order.enums.Occupancy;
import ogorek.wojciech.domain.model.seance.repository.SeanceRepository;
import ogorek.wojciech.domain.model.seat.repository.SeatRepository;
import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.domain.model.ticket.dto.GetTicketDto;
import ogorek.wojciech.domain.model.ticket.dto.validator.CreateTicketDtoValidator;
import ogorek.wojciech.domain.model.ticket.repository.TicketRepository;
import ogorek.wojciech.domain.model.user.repository.UserRepository;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
//    private final EmailService emailService;

    @Value("${ticket.standard.price}")
    private BigDecimal price;


    public List<GetTicketDto> findAllTickets() {
        return ticketRepository
                .findAll()
                .stream()
                .map(Ticket::toGetTicketDto)
                .collect(Collectors.toList());
    }

    public List<GetTicketDto> findAllTicketsByIds(List<Long> ids) {
        return ticketRepository
                .findAllById(ids)
                .stream()
                .map(Ticket::toGetTicketDto)
                .collect(Collectors.toList());
    }

    public GetTicketDto findTicketById(Long id) {
        return ticketRepository
                .findById(id)
                .stream()
                .map(Ticket::toGetTicketDto)
                .findFirst()
                .orElseThrow(() -> new AppServiceException("Fail fiding a this ticket id in db: " + id));
    }

    public GetTicketDto addTicket(CreateTicketDto createTicketDto) {
        Validator.validate(new CreateTicketDtoValidator(), createTicketDto);

        if(userRepository.findById(createTicketDto.getUserId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such user id in db");

        }else if(seanceRepository.findById(createTicketDto.getSeanceId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such seance id in db");

        }else if(seatRepository.findById(createTicketDto.getSeatId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such seat id in db");
        }

        var ticket = createTicketDto.toTicket();
        return ticketRepository
                .add(ticket)
                .map(Ticket::toGetTicketDto)
                .orElseThrow();
    }

    public GetTicketDto updateTicket(CreateTicketDto createTicketDto) {
        Validator.validate(new CreateTicketDtoValidator(), createTicketDto);

        if(userRepository.findById(createTicketDto.getUserId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such user id in db");

        }else if(seanceRepository.findById(createTicketDto.getSeanceId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such seance id in db");

        }else if(seatRepository.findById(createTicketDto.getSeatId()).isEmpty()){
            throw new AppServiceException("Fail to add ticket. There is no such seat id in db");
        }

        var ticket = createTicketDto.toTicket();
        return ticketRepository
                .update(ticket)
                .map(Ticket::toGetTicketDto)
                .orElseThrow();
    }

    public GetTicketDto deleteTicket(Long id) {
        return ticketRepository
                .delete(id)
                .map(Ticket::toGetTicketDto)
                .orElseThrow();
    }

    public boolean deleteAllTickets() {
        return ticketRepository
                .deleteAll();
    }

    public List<GetTicketDto> findTicketByUserNameAndSurname(String username) {
        return ticketRepository
                .findTicketByUsername(username)
                .stream()
                .map(Ticket::toGetTicketDto)
                .collect(Collectors.toList());
    }

    //---------------------------------BUYING TICKET-------------------------------------

//
//    public Long orderATicket(CreateOrderDto createOrderDto) {
//        if (Objects.isNull(createOrderDto)) {
//            throw new AppServiceException("getOrder cannot be null");
//        }
//        if (createOrderDto.getSeatIds().forEach(id ->
//        ))
//
//
//            var discounts = discounts()
//                    .entrySet()
//                    .stream()
//                    .filter(o -> o.getKey().equals(CreateOrderDto.getOccupancies().stream().))
//                    .map(Map.Entry::getValue)
//                    .findFirst()
//                    .orElseThrow();
//
//
//        var user = userRepository.getUserByUsername(createOrderDto.getUsername());
//
//        var ticket = CreateTicketDto
//                .builder()
//                .seanceId(CreateOrderDto.getSeanceId())
//                .discount(discounts)
//                .price(NORMAL_PRICE.multiply(BigDecimal.ONE.subtract(discount)))
//                .userId(user.getId())
//                .seatId()
//                .build();
//
//
//        emailService.orderToMail(user.getEmail(), ticket);
//
//    }

    static Map<Occupancy, BigDecimal> discounts() {
        return Map.ofEntries(
                Map.entry(Occupancy.FAMILY, new BigDecimal("0.2")),
                Map.entry(Occupancy.GROUP, new BigDecimal("0.3")),
                Map.entry(Occupancy.MINOR, new BigDecimal("0.1")),
                Map.entry(Occupancy.SENIOR, new BigDecimal("0.2")),
                Map.entry(Occupancy.STUDENT, new BigDecimal("0.2"))
        );
    }




}
