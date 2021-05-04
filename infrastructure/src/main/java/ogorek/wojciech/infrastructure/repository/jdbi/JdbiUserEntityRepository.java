package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.user.views.UserHistory;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;
import ogorek.wojciech.infrastructure.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface JdbiUserEntityRepository extends CrudRepository<UserEntity, Long> {

    List<UserWithTicket> getUserWithTickets(Long userId);
    List<UserHistory> getUserHistory(Long userId);
    Optional<UserEntity> getUserByNameAndSurname(String name, String surname);
    Optional<UserEntity> getUserByUsername(String username);
}
