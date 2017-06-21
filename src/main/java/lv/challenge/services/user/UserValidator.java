package lv.challenge.services.user;

import lv.challenge.domain.users.User;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 23.04.2017.
 */
@Component
public class UserValidator implements Validator<User> {
  @Override
  public void validate(User entity, Purpose purpose) throws ValidationError {

  }
}
