package ogorek.wojciech.infrastructure.repository;

import com.google.common.base.CaseFormat;
import ogorek.wojciech.domain.configs.repository.CrudRepository;
import ogorek.wojciech.infrastructure.repository.exception.AbstractCrudRepositoryException;
import org.atteo.evo.inflector.English;
import org.jdbi.v3.core.Jdbi;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractCrudRepository<T, ID> implements CrudRepository<T, ID> {
    protected final Jdbi jdbi;

    private final Class<T> entityType = (Class<T>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private final String TABLE_NAME = English.plural(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityType.getSimpleName()));

    public AbstractCrudRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }


    @Override
    public List<T> findAll() {
        final String SQL = "select * from " + TABLE_NAME + ";";

        return jdbi.withHandle(handle ->
                handle.createQuery(SQL)
                        .mapToBean(entityType)
                        .list());
    }

    @Override
    public List<T> findAllById(List<ID> ids) {
        final String IDS = ids
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));

        final String SQL = "select * from" + TABLE_NAME + "where id in (" + IDS + ");";

        return jdbi.withHandle(handle ->
                handle.createQuery(SQL)
                        .mapToBean(entityType)
                        .list());
    }

    @Override
    public Optional<T> findById(ID id) {
        final String SQL = "select * from" + TABLE_NAME + "where id = :id";
        return jdbi.withHandle(handle ->
                handle.createQuery(SQL)
                        .bind("id", id)
                        .mapToBean(entityType)
                        .findFirst());
    }

    @Override
    public Optional<T> findLast() {
        final String SQL = "select * from" + TABLE_NAME + "order by id desc limit 1";

        return jdbi.withHandle(handle ->
                handle.createQuery(SQL)
                        .mapToBean(entityType)
                        .findFirst());
    }

    @Override
    public Optional<T> add(T t) {
        final String COLUMN_NAMES = columnNamesToInsert();
        final String COLUMN_VALUES = columnValuesToInsert(t);
        final String SQL = "insert into " + TABLE_NAME + "("+ COLUMN_NAMES +") values (" + COLUMN_VALUES +");";
        var result =jdbi.withHandle(handle -> handle.execute(SQL));

        if(result == 0){
            return Optional.empty();
        }
        return findLast();
    }

    @Override
    public Optional<T> update(T t) {
        final String COLUMN_AND_VALUES = columnAndValuesForUpdate(t);
        final String SQL = "update " + TABLE_NAME + " set (" + COLUMN_AND_VALUES +") where id = " + getId(t);
        var result = jdbi.withHandle(handle -> handle.execute(SQL));

        if(result == 0){
            return Optional.empty();
        }

        return findById(getId(t));
    }

    @Override
    public Optional<T> delete(ID id) {
        final String SQL = "delete from " + TABLE_NAME + " where id = :id";

        var deletedOp = findById(id);

        var result = jdbi
                .withHandle(handle -> handle
                        .createUpdate(SQL)
                        .bind("id", id)
                        .execute());

        if(result == 0){
            return Optional.empty();
        }

        return deletedOp;
    }

    @Override
    public boolean deleteAll() {
        final String SQL = "delete from " + TABLE_NAME + ";";
        var result = jdbi.withHandle(handle -> handle.execute(SQL));
        return result == 0;
    }

    private String columnNamesToInsert() {
        return Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().toLowerCase().equals("id"))
                .map(field -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()))
                .collect(Collectors.joining(","));
    }

    private String columnValuesToInsert(T t){

        return Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().toLowerCase().equals("id"))
                .map(field -> {
                    try{
                        field.setAccessible(true);
                        if(field.getType().equals(String.class)){
                            return "'" + field.get(t) + "'";
                        }
                        if(Enum.class.isAssignableFrom(field.getType())){
                            return "'" + field.get(t) + "'";
                        }
                        if(field.getType().equals(LocalDateTime.class)){
                            var localDateTime = (LocalDateTime)field.get(t);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String formattedLocalDateTime = localDateTime.format(formatter);
                            return "'" + formattedLocalDateTime + "'";
                        }
                        return field.get(t).toString();

                    }catch(Exception e){
                        throw new AbstractCrudRepositoryException("inserting column values is invalid: " + e.getMessage());
                    }
                }).collect(Collectors.joining(","));

    }

    private String columnAndValuesForUpdate(T t){

        return Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().toLowerCase().equals("id"))
                .map(field -> {
                    try{
                        field.setAccessible(true);
                        if(field.getType().equals(String.class)){
                            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()) + "= '" + field.get(t) + "'";
                        }
                        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()) + " = " + field.get(t);
                    }catch(Exception e){
                        throw new AbstractCrudRepositoryException("updating column and values is invalid: " + e.getMessage());
                    }
                }).collect(Collectors.joining(","));
    }

    private ID getId(T t){
        try{
            Field field = entityType.getField("id");
            field.setAccessible(true);
            return (ID)field.get(t);
        }catch (Exception e){
            throw new AbstractCrudRepositoryException("get id is invalid: " + e.getMessage());
        }
    }


}
