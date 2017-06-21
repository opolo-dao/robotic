package lv.challenge.services.user;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.competitors.UserDAO;
import lv.challenge.domain.users.User;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Daniil on 24.04.2017.
 */
@Component
public class UserService extends CompetitorService<User> {
    public UserService(CompetitorsDAO<User> competitorsDAO,
                       Validator<User> validator) {
        this.dao = competitorsDAO;
        this.validator = validator;
    }

public Optional<User> findByLogin(String login){
        return ((UserDAO) dao).findByLogin(login);
}

    @Override
    public Optional<User> getByIdWithCollections(Integer id) {
        return null;
    }
}
