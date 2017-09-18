package lv.challenge.services.team;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Team;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 20.04.2017.
 */
@Component
public class TeamValidator implements Validator<Team> {

    private CompetitorsDAO<Team> dao;

    @Autowired
    public TeamValidator(CompetitorsDAO<Team> dao) {
        this.dao = dao;
    }

    @Override
    public void validate(Team entity, Purpose purpose) throws ValidationError {
        Map<String, String> errorsMap = new HashMap<>();
        if (purpose == Purpose.CREATE) {
            if (dao.isNameAlreadyExist(entity.getName()))
                errorsMap.put("teamNameErrorMsg", "This team name already exist. Please input different team name!");
        }
        if (purpose == Purpose.UPDATE) {
            List<Team> teamList = dao.getByFieldValue("name", entity.getName());
            if (!teamList.isEmpty()) {
                if (teamList.get(0).getId() != entity.getId()) {
                    errorsMap.put("teamNameErrorMsg", "This team name already exist. Please input different team name!");
                }
                dao.removeFromSessionCache(teamList.get(0));
            }
        }
        if (errorsMap.size() != 0) throw new TeamValidationException("Team object validation error", errorsMap);
    }
}
