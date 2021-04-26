package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    //todo check it
    private final SeatService seatService;
    private final EmailService emailService;

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

    /*
    *
    * ---- BUYING A TICKET ----
    *
    */

    public List<Long> orderATicket(CreateOrderDto createOrderDto) {
        if (Objects.isNull(createOrderDto)) {
            throw new AppServiceException("getOrder cannot be null");
        }

        if (!seatService.getSeatState(createOrderDto.getSeatIds())) {
            throw new AppServiceException("order dto is invalid - seats are not free");
        }

        var user = userRepository
                .getUserByUsername(createOrderDto.getUsername())
                .orElseThrow(() -> new AppServiceException("create order dto username is invalid. There is no such user in db"));

        var createTicketsDtos = getTickets(createOrderDto);

        var totalPrice = totalPrice(createTicketsDtos);
        emailService.orderToMail(user.toGetUserDto().getEmail(), createTicketsDtos, totalPrice);
        return createTicketsDtos
                .stream()
                .map(CreateTicketDto::toTicket)
                .map(Ticket::toGetTicketDto)
                .map(GetTicketDto::getId)
                .collect(Collectors.toList());
    }


    private List<CreateTicketDto> getTickets(CreateOrderDto createOrderDto) {

        var createTicketsDto = new ArrayList<CreateTicketDto>();

        IntStream.range(0, fromCreateOrderToSeatsAndDiscounts(createOrderDto).size())
                .boxed()
                .forEach(i -> {
                    var createTicketDto = CreateTicketDto.builder()
                            .seanceId(createOrderDto.getSeanceId())
                            .price(price)
                            .seatId(new ArrayList<>(fromCreateOrderToSeatsAndDiscounts(createOrderDto).keySet()).get(i))
                            .state(createOrderDto.getState())
                            .userId(userRepository.getUserByUsername(createOrderDto.getUsername()).orElseThrow().toGetUserDto().getId())
                            .discount(discounts().entrySet().stream()
                                    .filter(occu -> occu
                                            .getKey()
                                            .equals(new ArrayList<>(fromCreateOrderToSeatsAndDiscounts(createOrderDto).values()).get(i)))
                                    .findFirst()
                                    .orElseThrow(() -> new AppServiceException("Creating discount is invalid"))
                                    .getValue())
                            .build();
                    createTicketsDto.add(createTicketDto);
                });

        return createTicketsDto;

    }

    private Map<Long, Occupancy> fromCreateOrderToSeatsAndDiscounts(CreateOrderDto createOrderDto) {
        if (createOrderDto.getSeatIds().size() != createOrderDto.getOccupancies().size()) {
            throw new AppServiceException("create order seats size and occupancies size is invalid. It must be equal");
        }
        return IntStream.range(0, createOrderDto.getSeatIds().size())
                .boxed()
                .collect(Collectors.toMap(
                        i -> createOrderDto.getSeatIds().get(i),
                        i -> createOrderDto.getOccupancies().get(i)));
    }

    private BigDecimal totalPrice(List<CreateTicketDto> createTicketsDtos) {
        return
                createTicketsDtos
                        .stream()
                        .map(CreateTicketDto::toTicket)
                        .map(Ticket::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

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
