package ogorek.wojciech.infrastructure.repository.jdbi.impl;

import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.SeanceEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiSeanceEntityRepository;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class JdbiSeanceEntityRepositoryImpl extends AbstractCrudRepository<SeanceEntity, Long> implements JdbiSeanceEntityRepository {
    public JdbiSeanceEntityRepositoryImpl(Jdbi jdbi) {
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
