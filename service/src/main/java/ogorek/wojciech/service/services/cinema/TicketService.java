package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.dto.SeatOccupancyDto;
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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    private final SeatService seatService;
    private final EmailService emailService;

    @Value("${ticket.standard.price}")
    private BigDecimal price;

    private Lock lock = new ReentrantLock(true);


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

        if (userRepository.findById(createTicketDto.getUserId()).isEmpty()) {
            throw new AppServiceException("Fail to add ticket. There is no such user id in db");

        } else if (seanceRepository.findById(createTicketDto.getSeanceId()).isEmpty()) {
            throw new AppServiceException("Fail to add ticket. There is no such seance id in db");

        } else if (seatRepository.findById(createTicketDto.getSeatId()).isEmpty()) {
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

        if (userRepository.findById(createTicketDto.getUserId()).isEmpty()) {
            throw new AppServiceException("Fail to add ticket. There is no such user id in db");

        } else if (seanceRepository.findById(createTicketDto.getSeanceId()).isEmpty()) {
            throw new AppServiceException("Fail to add ticket. There is no such seance id in db");

        } else if (seatRepository.findById(createTicketDto.getSeatId()).isEmpty()) {
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

    public List<GetTicketDto> findTicketByUsername(String username) {
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

    public List<GetTicketDto> orderATicket(CreateOrderDto createOrderDto) {
        try {
            lock.lock();

            if (Objects.isNull(createOrderDto)) {
                throw new AppServiceException("Order a ticket failed. Order is null.");
            }

            var seatIds = createOrderDto
                    .getSeatOccupancy()
                    .stream()
                    .map(SeatOccupancyDto::getSeatId)
                    .collect(Collectors.toList());

            if (!seatService.getSeatState(seatIds)) {
                throw new AppServiceException("order dto is invalid - seats are not free");
            }

            var user = userRepository
                    .getUserByUsername(createOrderDto.getUsername())
                    .orElseThrow(() -> new AppServiceException("create order dto username is invalid. There is no such user in db"));

            var createTicketsDto = getTickets(createOrderDto);
            var totalPrice = totalPrice(createTicketsDto);
            emailService.orderToMail(user.toGetUserDto().getEmail(), createTicketsDto, totalPrice);

            return createTicketsDto
                    .stream()
                    .map(CreateTicketDto::toTicket)
                    .map(Ticket::toGetTicketDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new AppServiceException("Order a ticket failed " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private List<CreateTicketDto> getTickets(CreateOrderDto createOrderDto) {

        var createTicketsDto = new ArrayList<CreateTicketDto>();

        var userId = userRepository
                .getUserByUsername(createOrderDto.getUsername())
                .orElseThrow()
                .toGetUserDto()
                .getId();

        IntStream.range(0, createOrderDto.getSeatOccupancy().size())
                .boxed()
                .forEach(i -> {
                    var discount = discounts()
                            .entrySet()
                            .stream()
                            .filter(o -> o.getKey().equals(createOrderDto.getSeatOccupancy().get(i).getOccupancy()))
                            .findFirst()
                            .orElseThrow()
                            .getValue();

                    var createTicketDto = CreateTicketDto.builder()
                            .seanceId(createOrderDto.getSeanceId())
                            .price(price)
                            .seatId(createOrderDto.getSeatOccupancy().get(i).getSeatId())
                            .state(createOrderDto.getState())
                            .userId(userId)
                            .discount(discount)
                            .build();

                    createTicketsDto.add(createTicketDto);
                });
        return createTicketsDto;
    }

    private BigDecimal totalPrice(List<CreateTicketDto> createTicketsDtos) {
        return
                createTicketsDtos
                        .stream()
                        .map(CreateTicketDto::toTicket)
                        .map(Ticket::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Map<Occupancy, BigDecimal> discounts() {
        return Map.ofEntries(
                Map.entry(Occupancy.REGULAR, BigDecimal.ZERO),
                Map.entry(Occupancy.FAMILY, new BigDecimal("0.2")),
                Map.entry(Occupancy.GROUP, new BigDecimal("0.3")),
                Map.entry(Occupancy.MINOR, new BigDecimal("0.1")),
                Map.entry(Occupancy.SENIOR, new BigDecimal("0.2")),
                Map.entry(Occupancy.STUDENT, new BigDecimal("0.2"))
        );
    }


}
