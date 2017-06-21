package lv.challenge.services.robot;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
@Component
public class RobotValidator implements Validator<Robot> {
    @Autowired
    private CompetitorsDAO<Robot> dao;

    @Override
    public void validate(Robot entity, Purpose purpose) throws ValidationError {
        Map<String, String> errorsList = new HashMap<>();
        if (purpose == Purpose.CREATE) {
            if (dao.isNameAlreadyExist(entity.getName())) errorsList.put("robotNameErrorMsg", "Already occupied");
        }
        if (purpose == Purpose.UPDATE) {
            List<Robot> list = dao.getByFieldValue("name",entity.getName());
            if(!list.isEmpty()) {
                if (list.get(0) != null) {
                    if (list.get(0).getId() != entity.getId()) {
                        errorsList.put("robotNameErrorMsg", "Already occupied");
                    }
                }
            }
        }
        if (entity.getCompetitions() == null)
            errorsList.put("robotCompetitionsErrorMsg", "Robot must be registered at least on one competition");
        if (entity.getOperators() == null) errorsList.put("robotOperatorsErrorMsg", "Robot must have at least one operator");
        if (errorsList.size() != 0) throw new

                RobotValidationError("Robot validation error", errorsList);
    }
}
