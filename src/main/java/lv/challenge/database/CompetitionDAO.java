package lv.challenge.database;

import java.util.Optional;

/**
 * Created by Daniil on 15.05.2017.
 */
public interface CompetitionDAO<T> {
    void save(T entity);

    void delete(T entity);
    Optional<T> getById(int id);
    Optional<T> loadById(int id);
}
