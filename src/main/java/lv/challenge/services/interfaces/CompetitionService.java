package lv.challenge.services.interfaces;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Daniil on 15.05.2017.
 */
@Transactional
public abstract class CompetitionService<T> {
    protected CompetitionDAO<T> dao;

    protected List<List<String>> prepareTable(List<T> records) {
        return null;
    }

    public void register(T entity) {

        dao.save(entity);
    }

    public void delete(T entity) {
    }

    public void deleteAllTournamentRecords(Tournament tournament) {
        dao.deleteAllTournamentRecords(tournament);
    }

    public List<List<String>> getAllAsStringArray() {
        return prepareTable(dao.getAll());
    }

    public List<T> getRobotAttempts(Robot robot, Tournament tournament) {
        return dao.getRobotResults(robot, tournament);
    }
}
