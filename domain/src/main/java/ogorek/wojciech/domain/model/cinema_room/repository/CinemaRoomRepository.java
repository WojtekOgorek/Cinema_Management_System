package ogorek.wojciech.domain.model.cinema_room.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.cinema_room.CinemaRoom;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeance;
import ogorek.wojciech.domain.model.cinema_room.views.CinemaRoomWithSeats;

import java.util.List;

public interface CinemaRoomRepository extends CrudRepository<CinemaRoom, Long> {
    List<CinemaRoomWithSeats> specificCinemaRoomWithSeats(Long cinemaRoomId);
    List<CinemaRoomWithSeance> specificCinemaRoomWithSeances(Long cinemaRoomId);

}
