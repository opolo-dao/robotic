package lv.challenge.services.user;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.competitors.UserDAO;
import lv.challenge.domain.users.User;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import lv.challenge.services.team.TeamValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Daniil on 23.04.2017.
 */
@Component
public class UserValidator implements Validator<User> {
    @Autowired
    CompetitorsDAO<User> dao;

    @Override
    public void validate(User entity, Purpose purpose) throws ValidationError {
        Map<String, String> errorsMap = new HashMap<>();
        if (purpose == Purpose.CREATE) {
            Optional<User> userOptional = ((UserDAO) dao).findByLogin(entity.getLogin());
            if (userOptional.isPresent()) {
                errorsMap.put("userLoginErrorMsg", "This login already occupied. Please input different login!");
            }
        }
            if (errorsMap.size() != 0) throw new TeamValidationException("User object validation error", errorsMap);

    }
}
