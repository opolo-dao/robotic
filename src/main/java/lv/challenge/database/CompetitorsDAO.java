package lv.challenge.database;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Daniil on 12.04.2017.
 */
public interface CompetitorsDAO<T> {
    T save(T entity);
    Optional<T> getById(int id);
    Optional<T> loadById(int id);
    List<T> getByFieldValue(String fieldName, String value);
    void deleteById(int id);
    void update(T entity);
    List<T> getAll();
    List<T> getByTeamId(Integer id);
    boolean isNameAlreadyExist(String name);
    Set<T> getByMultipleIds(List<Integer> list);

}
