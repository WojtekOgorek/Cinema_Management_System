package ogorek.wojciech.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.seance.Seance;
import ogorek.wojciech.domain.model.seance.repository.SeanceRepository;
import ogorek.wojciech.domain.model.seance.views.SeanceByDate;
import ogorek.wojciech.infrastructure.repository.AbstractCrudRepository;
import ogorek.wojciech.infrastructure.repository.entity.SeanceEntity;
import ogorek.wojciech.infrastructure.repository.jdbi.JdbiSeanceEntityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SeanceRepositoryImpl implements SeanceRepository {

    private final JdbiSeanceEntityRepository jdbiSeanceEntityRepository;

    @Override
    public List<Seance> findAll() {
        return jdbiSeanceEntityRepository
                .findAll()
                .stream()
                .map(SeanceEntity::toSeance)
                .collect(Collectors.toList());
    }

    @Override
    public List<Seance> findAllById(List<Long> ids) {
        return jdbiSeanceEntityRepository
                .findAllById(ids)
                .stream()
                .map(SeanceEntity::toSeance)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Seance> findById(Long id) {
        return jdbiSeanceEntityRepository
                .findById(id)
                .map(SeanceEntity::toSeance);
    }

    @Override
    public Optional<Seance> findLast() {
        return jdbiSeanceEntityRepository
                .findLast()
                .map(SeanceEntity::toSeance);
    }

    @Override
    public Optional<Seance> add(Seance seance) {
        return jdbiSeanceEntityRepository
                .add(new SeanceEntity().fromSeance(seance))
                .map(SeanceEntity::toSeance);
    }

    @Override
    public Optional<Seance> update(Seance seance) {
        return jdbiSeanceEntityRepository
                .update(new SeanceEntity().fromSeance(seance))
                .map(SeanceEntity::toSeance);
    }

    @Override
    public Optional<Seance> delete(Long id) {
        return jdbiSeanceEntityRepository
                .delete(id)
                .map(SeanceEntity::toSeance);
    }

    @Override
    public boolean deleteAll() {
        return jdbiSeanceEntityRepository
                .deleteAll();
    }

    @Override
    public List<SeanceByDate> getSeanceByDate(String dateFrom, String dateTo) {
        return jdbiSeanceEntityRepository
                .getSeanceByDate(dateFrom,dateTo);
    }
}
