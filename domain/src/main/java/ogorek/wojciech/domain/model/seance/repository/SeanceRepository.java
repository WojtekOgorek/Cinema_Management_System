package ogorek.wojciech.domain.model.seance.repository;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.seance.Seance;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;

import java.util.List;


public interface SeanceRepository extends CrudRepository<Seance, Long> {

    List<SeanceByDate> getSeanceByDate(String dateFrom, String dateTo);
}
