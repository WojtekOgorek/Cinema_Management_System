package ogorek.wojciech.infrastructure.repository.jdbi;

import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.infrastructure.repository.entity.SeanceEntity;

import java.util.List;

public interface JdbiSeanceEntityRepository extends CrudRepository<SeanceEntity,Long> {

    List<SeanceByDate> getSeanceByDate(String dateFrom, String dateTo);
}
