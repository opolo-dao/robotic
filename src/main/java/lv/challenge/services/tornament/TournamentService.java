package lv.challenge.services.tornament;

import lv.challenge.database.TournamentDAO;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<List<String>> getAllCompetitors() {
        return prepareTable(dao.getAllRobots());
    }

    public List<List<String>> getCompetitionRobots(CompetitionType competition) {
        return prepareTable(dao.getCompetitionRobots(competition));
    }

    private List<List<String>> prepareTable(List<Robot> list) {
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
            result.add(record);
        }
        return result;
    }
}
