package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.ticket.views.TicketInfo;
import ogorek.wojciech.infrastructure.repository.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface JdbiTicketEntityRepository extends CrudRepository<TicketEntity, Long> {

    List<TicketEntity> findTicketByUsername(String username);
    Optional<TicketInfo> findTicketInfo(Long id);
}
