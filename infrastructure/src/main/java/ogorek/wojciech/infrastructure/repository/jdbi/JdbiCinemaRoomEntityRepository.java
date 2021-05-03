package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;
import ogorek.wojciech.infrastructure.repository.entity.CinemaRoomEntity;

import java.util.List;

public interface JdbiCinemaRoomEntityRepository extends CrudRepository<CinemaRoomEntity, Long> {

    List<CinemaRoomWithSeats> specificCinemaRoomWithSeats(Long cinemaRoomId);

    List<CinemaRoomWithSeance> specificCinemaRoomWithSeances(Long cinemaRoomId);
}
