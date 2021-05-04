package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.user.User;
import ogorek.wojciech.domain.model.user.repository.UserRepository;
import ogorek.wojciech.domain.model.user.views.UserHistory;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;
import ogorek.wojciech.infrastructure.repository.entity.UserEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiUserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbiUserEntityRepository jdbiUserEntityRepository;

    @Override
    public List<User> findAll() {
        return jdbiUserEntityRepository
                .findAll()
                .stream()
                .map(UserEntity::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return jdbiUserEntityRepository
                .findAllById(ids)
                .stream()
                .map(UserEntity::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbiUserEntityRepository
                .findById(id)
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findLast() {
        return jdbiUserEntityRepository
                .findLast()
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> add(User user) {
        return jdbiUserEntityRepository
                .add(new UserEntity().fromUser(user))
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> update(User user) {
        return jdbiUserEntityRepository
                .update(new UserEntity().fromUser(user))
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> delete(Long id) {
        return jdbiUserEntityRepository
                .delete(id)
                .map(UserEntity::toUser);
    }

    @Override
    public boolean deleteAll() {
        return jdbiUserEntityRepository
                .deleteAll();
    }

    @Override
    public List<UserWithTicket> getUserWithTickets(Long userId) {
        return jdbiUserEntityRepository
                .getUserWithTickets(userId);
    }

    @Override
    public List<UserHistory> getUserHistory(Long userId) {
        return jdbiUserEntityRepository
                .getUserHistory(userId);
    }

    @Override
    public Optional<User> getUserByNameAndSurname(String name, String surname) {
        return jdbiUserEntityRepository
                .getUserByNameAndSurname(name,surname)
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return jdbiUserEntityRepository
                .getUserByUsername(username)
                .map(UserEntity::toUser);
    }
}
