package lv.challenge.services.team;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 20.04.2017.
 */
public class TeamValidationException extends ValidationError {

  public TeamValidationException(String message, Map<String, String> errorList) {
    super(message, errorList);
  }
}
