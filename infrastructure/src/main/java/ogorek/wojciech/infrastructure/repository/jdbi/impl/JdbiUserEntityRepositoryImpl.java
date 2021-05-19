package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.user.views.UserHistory;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.UserEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiUserEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbiUserEntityRepositoryImpl extends AbstractCrudRepository<UserEntity, Long> implements JdbiUserEntityRepository {
    public JdbiUserEntityRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }
    @Override
    public List<UserWithTicket> getUserWithTickets(Long userId) {
        final String SQL = """
                     select 
                       u.id,
                       t.id
                       from users u 
                       join tickets t on u.id = t.user_id
                       where u.id = :id                 
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", userId)
                .mapToBean(UserWithTicket.class)
                .list());
    }


    @Override
    public List<UserHistory> getUserHistory(Long userId) {
        final String SQL = """
                select
                u.id,
                c.name,
                m.title,
                s.date_time,
                t.price
                from tickets t
                join users u on u.id = t.user_id
                join seances s on s.id = t.seance_id
                join movies m on s.movie_id = m.id
                join cinema_rooms cr on cr.id = s.cinema_room_id
                join cinemas c on c.id = cr.cinema_id
                where u.id = :id
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", userId)
                .mapToBean(UserHistory.class)
                .list()
        );
    }

    @Override
    public Optional<UserEntity> getUserByNameAndSurname(String name, String surname) {

        final String SQL = """
                select
                u.id
                from users u
                where u.name = :name and u.surname = :surname
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("name", name)).bind("surname", surname)
                .mapToBean(UserEntity.class)
                .findFirst();
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        final String SQL = """
                select
                u.id
                from users u
                where u.username = :username
                """;
        return jdbi.withHandle(handle -> handle
                .createQuery(SQL)
                .bind("username", username)
                .mapToBean(UserEntity.class)
                .findFirst());

    }
}
