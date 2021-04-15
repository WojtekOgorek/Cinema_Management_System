package ogorek.wojciech.domain.model.user.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.views.UserHistory;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<UserWithTicket> getUserWithTickets(Long userId);
    List<UserHistory> getUserHistory(Long userId);
    Optional<User> getUserByNameAndSurname(String name, String surname);
    Optional<User> getUserByUsername(String username);

}
