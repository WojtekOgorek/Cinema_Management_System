package ogorek.wojciech.infrastructure.web.routing;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.JsonConverter;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
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

    private final TicketService ticketService;

    private final Gson gson;


    public void initUserRouting() {
        // /tickets
        path("/tickets", () -> {

            //TICKET GENERAL CRUD
            get("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.findAllTickets();
            }, new JsonTransformer());

            post("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var ticketToAdd = new JsonConverter<CreateTicketDto>(request.body())
                        .fromJson()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid json body for ticket add"));
                return ticketService.addTicket(ticketToAdd);
            }, new JsonTransformer());

            delete("", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.deleteAllTickets();
            }, new JsonTransformer());

            // /tickets/:id
            path("/:id", () -> {

                get("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return ticketService.findTicketById(Long.parseLong(request.params("id")));
                }, new JsonTransformer());

                put("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    var ticketToUpdate = new JsonConverter<CreateTicketDto>(request.body())
                            .fromJson()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid json body for ticket update"));
                    return ticketService.updateTicket(ticketToUpdate);
                }, new JsonTransformer());

                delete("", (request, response) -> {
                    response.header(contentTypeHeader, contentTypeHeaderValue);
                    return ticketService.deleteTicket(Long.parseLong(request.params("id")));
                }, new JsonTransformer());
            });

            //todo check it, swap to object
            get(":ids", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                var ids =
                        Arrays
                                .stream(request.params(request.body()).split(";"))
                                .mapToLong(Long::getLong)
                                .boxed()
                                .collect(Collectors.toList());
                return ticketService.findAllTicketsByIds(ids);
            }, new JsonTransformer());

            //SPECIAL TICKETS CRUD
            //tickets/:name/:surname
            get("/:username", (request, response) -> {
                response.header(contentTypeHeader, contentTypeHeaderValue);
                return ticketService.findTicketByUserNameAndSurname(request.params("username"));
            }, new JsonTransformer());
        });
    }
}
