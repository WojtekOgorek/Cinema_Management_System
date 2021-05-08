# Cinema Management System

#### -> UNDER CONSTRUCTION <-> ALMOST FINISHED <-

Cinema Management System is a small REST-API that provides management system for cinema network. MySql database contains
data of cities, cinemas, cinema_rooms, movies, seances, seats, tickets, users, user favourites. It gives the user
possibility to buy, reserve ticket/s on picked seances in different cities/cinemas. It lets admin to manage CRUD system,
get statistics from db and create cinema finance policy. The Rest-Api is created based on DDD and TDD convention. The
domain module is fully encapsulated from rest of the application.

###Technologies used: Java, Maven, Jdbi, Spark, Spring.

## Installation

Use maven -> [link](https://maven.apache.org/download.cgi) <- to install client product shop.

```bash
#main folder
mvn clean install
#go to ui folder 
cd infrastructure
#go to target folder
cd target
#start app
java -jar --enable-preview infrastructure.jar
```

## Usage

```java
/*
 *
 * ------------------- TICKET DOMAIN MODEL -------------------
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Ticket {
    Long id;
    Long seanceId;
    Long seatId;
    BigDecimal price;
    BigDecimal discount;
    State state;
    Long userId;

    public GetTicketDto toGetTicketDto() {
        return GetTicketDto
                .builder()
                .id(id)
                .seanceId(seanceId)
                .seatId(seatId)
                .price(price)
                .discount(discount)
                .state(state)
                .userId(userId)
                .build();
    }

    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.ONE.subtract(discount));
    }

}

/*
 *
 * ------------------ BUYING A TICKET ------------------
 *
 * 
 */
public class buyingTicketPreview() {
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
}
/*
 *
 * ------------------ TICKET ROUTING ------------------
 *
 *
 */
public class ticketRoutingPreview(){
    
    @Component
    @RequiredArgsConstructor
    public class TicketRouting {


        @Value("${http.response.header.content-type}")
        private String contentTypeHeader;

        @Value("${http.response.header.content-type.value")
        private String contentTypeHeaderValue;

        private final TicketService ticketService;

        public void initUserRouting() {
            // /ticket
            path("/ticket", () -> {

                //TICKET GENERAL CRUD
                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return ticketService.findAllTickets();
                }, new JsonTransformer());

                post("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var ticketToAdd = new CreateTicketDtoJsonConverter(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for ticket add"));
                    return ticketService.addTicket(ticketToAdd);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return ticketService.deleteAllTickets();
                }, new JsonTransformer());

                // /ticket/:id
                path("/:id", () -> {

                    get("", (request, response) -> {
                        response.header(contentTypeHeader, contentTypeHeaderValue);
                        return ticketService.findTicketById(Long.parseLong(request.params("id")));
                    }, new JsonTransformer());

                    put("", (request, response) -> {
                        response.header(contentTypeHeader, contentTypeHeaderValue);
                        var ticketToUpdate = new CreateTicketDtoJsonConverter(request.body())
                                .fromJson()
                                .orElseThrow(() -> new IllegalArgumentException("Invalid json body for ticket update"));
                        return ticketService.updateTicket(ticketToUpdate);
                    }, new JsonTransformer());

                    delete("", (request, response) -> {
                        response.header(contentTypeHeader, contentTypeHeaderValue);
                        return ticketService.deleteTicket(Long.parseLong(request.params("id")));
                    }, new JsonTransformer());
                });
                
                //ticket?ids=1,2,3,4,5;
                get("?ids=:ids", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var ids =
                            Arrays
                                    .stream(request.params(request.body()).split(","))
                                    .mapToLong(Long::getLong)
                                    .boxed()
                                    .collect(Collectors.toList());
                    return ticketService.findAllTicketsByIds(ids);
                }, new JsonTransformer());

                //SPECIAL TICKETS CRUD
                //ticket/:username
                get("/:username", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return ticketService.findTicketByUsername(request.params("username"));
                }, new JsonTransformer());


                post("/order", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var tickets = new CreateOrderDtoJsonConverter(request.body())
                            .fromJson()
                            .orElseThrow(() -> new AppConverterException("Order json body request is invalid"));
                    return ticketService.orderATicket(tickets);
                }, new JsonTransformer());
            });
        }
    }
}
```