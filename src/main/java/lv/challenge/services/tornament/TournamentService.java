package lv.challenge.services.tornament;

import lv.challenge.application.ApplicationService;
import lv.challenge.database.TournamentDAO;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Daniil on 24.06.2017.
 */
@Component
@Transactional
public class TournamentService {
    @Autowired
    TournamentDAO dao;
    @Autowired
    Validator<Tournament> validator;
    @Autowired
    ApplicationService appService;

    public Map<String, String> save(Tournament tournament) {
        Map<String, String> errors = new HashMap<>();
        try {
            validator.validate(tournament, Validator.Purpose.CREATE);
        } catch (ValidationError validationError) {
            errors.putAll(validationError.getErrorsMap());
        }
        if (errors.isEmpty()) {
            dao.save(tournament);
        }
        return errors;
    }

    public void delete(Tournament tournament) {
        dao.delete(tournament);
    }

    public List<Tournament> getAll() {
        return dao.getAll();
    }

    public List<List<String>> getAllCompetitors() {
        return prepareTable(dao.getAllRobots(), false);
    }

    public List<List<String>> getCompetitionRobots(CompetitionType competition) {
        return prepareTable(dao.getCompetitionRobots(competition), false);
    }

    public List<List<String>> getRobotsToAccept() {
        return prepareTable(dao.getRobotsToAccept(), true);

    }

    private List<List<String>> prepareTable(List<Robot> list, boolean withAccept) {
        List<List<String>> result = new ArrayList<>();
        for (Robot robot : list) {
            List<String> record = new ArrayList<>();
            record.add(robot.getName());
            record.add(robot.getTeam().getName());
            record.add(robot.getTeam().getUser().getOrganisation());
            String operators = robot.getOperators().stream()
                    .map(p -> {
                        return p.getName() + " " + p.getSurname();
                    })
                    .reduce((s1, s2) -> s1 + "," + s2)
                    .orElse("");
            record.add(operators);
            String competitions = robot.getCompetitions().stream()
                    .map(e -> e.goodLook()).reduce((s1, s2) -> s1 + "," + s2)
                    .orElse("");
            record.add(competitions);
            if (withAccept) record.add(Integer.toString(robot.getId()));
            result.add(record);
        }
        return result;
    }

    public Long getNumberOfRobotsToCheck() {
        return dao.getNumberOfRobotsToCheck();
    }

    public Map<String, List<String>> getTournamentStatistic(Integer tournamentId) {
        Map<String, List<String>> stats = new HashMap<>();
        stats.put("countries", dao.getUniqCountries(tournamentId));
        return stats;
    }

    public void setActiveTournament(Integer id) {
        Optional<Tournament> activeTournament = dao.getActiveTournament();
        if (activeTournament.isPresent() && activeTournament.get().getId().equals(id)) return;
        Optional<Tournament> newActiveTournament = dao.loadById(id);
        if (newActiveTournament.isPresent()) {
            if (activeTournament.isPresent()) {
                activeTournament.get().setActive(false);
                dao.update(activeTournament.get());
            }
            newActiveTournament.get().setActive(true);
            dao.update(newActiveTournament.get());
            appService.setActiveTournament(newActiveTournament.get());
        }
    }

    public Optional<Tournament> getActiveTournament() {
        return dao.getActiveTournament();
    }
}
