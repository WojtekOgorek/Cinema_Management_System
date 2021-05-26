package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.order.dto.CreateOrderDto;
import ogorek.wojciech.domain.model.order.dto.converter.CreateOrderDtoJsonConverter;
import ogorek.wojciech.domain.model.seat.dto.CreateSeatDto;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.domain.model.ticket.dto.converter.CreateTicketDtoJsonConverter;
import ogorek.wojciech.infrastructure.web.transformer.JsonTransformer;
import ogorek.wojciech.service.services.cinema.TicketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class TicketRouting {


    @Value("${http.response.header.content-type}")
    private String contentTypeHeader;

    @Value("${http.response.header.content-type.value")
    private String contentTypeHeaderValue;

    private final JsonTransformer jsonTransformer;

    private final TicketService ticketService;


    public void initUserRouting() {
        // /api/ticket
        path("/api/ticket", () -> {

            //TICKET GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.findAllTickets();
            }, jsonTransformer);

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var ticketToAdd = jsonTransformer.fromJson(request.body(), CreateTicketDto.class);
                return ticketService.addTicket(ticketToAdd);
            }, jsonTransformer);

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.deleteAllTickets();
            }, jsonTransformer);

            // /api/ticket/id/:id
            path("/id/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return ticketService.findTicketById(id);
                }, jsonTransformer);

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    var ticketToUpdate = jsonTransformer.fromJson(request.body(), CreateTicketDto.class);
                    return ticketService.updateTicket(id, ticketToUpdate);
                }, jsonTransformer);

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var id = Long.parseLong(request.params("id"));
                    return ticketService.deleteTicket(id);
                }, jsonTransformer);
            });

            //  /api/ticket/many?ids=1,2,3,4,5;
            get("/many", (request, response) -> {
                var params = request.queryParams("ids");
                response.header(contentTypeHeader, contentTypeHeaderValue);

                var ids = Arrays
                        .stream(params.split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());


                return ticketService.findAllTicketsByIds(ids);
            }, jsonTransformer);

            //SPECIAL TICKETS CRUD
            //  /api/ticket/username?username=Username 1
            get("/username", (request, response) -> {
                var params = request.queryParams("username");
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.findTicketByUsername(params);
            }, jsonTransformer);

            // /api/ticket/order
            post("/order", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var tickets = jsonTransformer.fromJson(request.body(), CreateOrderDto.class);
                return ticketService.orderATicket(tickets);
            }, jsonTransformer);
        });
    }
}
