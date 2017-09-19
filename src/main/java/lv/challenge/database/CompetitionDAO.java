package lv.challenge.database;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;

import java.util.List;
import java.util.Optional;

/**
 * Created by Daniil on 15.05.2017.
 */
public interface CompetitionDAO<T> {
    void save(T entity);

    void delete(T entity);
    Optional<T> getById(int id);
    Optional<T> loadById(int id);

    List<T> getAll();

    void deleteAllTournamentRecords(Tournament tournament);
    List<T> getRobotResults(Robot robot, Tournament tournament);
}
