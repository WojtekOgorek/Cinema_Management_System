package ogorek.wojciech.domain.model.ticket.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.views.TicketInfo;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findTicketByUsername(String username);
    Optional<TicketInfo> findTicketInfo(Long id);
}
