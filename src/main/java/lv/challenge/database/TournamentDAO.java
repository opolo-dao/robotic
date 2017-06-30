package lv.challenge.database;

import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;

import java.util.List;
import java.util.Optional;

/**
 * Created by Daniil on 24.06.2017.
 */
public interface TournamentDAO {
    void save(Tournament entity);

    Optional<Tournament> loadById(int id);

    void deleteById(int id);

    void delete(Tournament tournament);

    void update(Tournament entity);

    List<Tournament> getAll();

    boolean isNameAlreadyExist(String name);

    Optional<Tournament> getByName(String name);

    List<Robot> getAllRobots();

    List<Robot> getCompetitionRobots(CompetitionType competition);
}
