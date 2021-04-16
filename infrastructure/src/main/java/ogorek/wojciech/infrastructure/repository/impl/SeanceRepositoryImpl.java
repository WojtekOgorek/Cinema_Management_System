package ogorek.wojciech.infrastructure.repository.impl;

import ogorek.wojciech.domain.model.seance.Seance;
import ogorek.wojciech.domain.model.seance.repository.SeanceRepository;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeanceRepositoryImpl extends AbstractCrudRepository<Seance, Long> implements SeanceRepository {
    public SeanceRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }


    @Override
    public List<SeanceByDate> getSeanceByDate(String dateFrom, String dateTo) {
        final String SQL = """
                select s.id,
                       s.date_time,
                       t.id
                from seances s
                join tickets t on s.id = t.seance_id
                where s.date_time BETWEEN :dateFrom and :dateTo
                and t.state = 'RESERVED' 
                                """;
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(SQL)
                        .bind("dateFrom", dateFrom)
                        .bind("dateTo", dateTo)
                        .mapToBean(SeanceByDate.class)
                        .list()
        );
    }

}
