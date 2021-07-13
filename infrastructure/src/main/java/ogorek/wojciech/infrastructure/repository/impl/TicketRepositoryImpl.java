package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.ticket.Ticket;
import ogorek.wojciech.domain.model.ticket.repository.TicketRepository;
import ogorek.wojciech.domain.model.ticket.views.TicketInfo;
import ogorek.wojciech.infrastructure.repository.entity.TicketEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiTicketEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final JdbiTicketEntityRepository jdbiTicketEntityRepository;

    @Override
    public List<Ticket> findAll() {
        return jdbiTicketEntityRepository
                .findAll()
                .stream()
                .map(TicketEntity::toTicket)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllById(List<Long> ids) {
        return jdbiTicketEntityRepository
                .findAllById(ids)
                .stream()
                .map(TicketEntity::toTicket)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return jdbiTicketEntityRepository
                .findById(id)
                .map(TicketEntity::toTicket);
    }

    @Override
    public Optional<Ticket> findLast() {
        return jdbiTicketEntityRepository
                .findLast()
                .map(TicketEntity::toTicket);
    }

    @Override
    public Optional<Ticket> add(Ticket ticket) {
        return jdbiTicketEntityRepository
                .add(new TicketEntity().fromTicket(ticket))
                .map(TicketEntity::toTicket);
    }

    @Override
    public Optional<Ticket> update(Ticket ticket) {
        return jdbiTicketEntityRepository
                .update(new TicketEntity().fromTicket(ticket))
                .map(TicketEntity::toTicket);
    }

    @Override
    public Optional<Ticket> delete(Long id) {
        return jdbiTicketEntityRepository
                .delete(id)
                .map(TicketEntity::toTicket);
    }

    @Override
    public boolean deleteAll() {
        return jdbiTicketEntityRepository
                .deleteAll();
    }

    @Override
    public List<Ticket> findTicketByUsername(String username) {
        return jdbiTicketEntityRepository
                .findTicketByUsername(username)
                .stream()
                .map(TicketEntity::toTicket)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TicketInfo> findTicketInfo(Long id) {
        return jdbiTicketEntityRepository
                .findTicketInfo(id);
    }
}
