package lv.challenge.services.user;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 23.04.2017.
 */
public class UserValidationError extends ValidationError {
  public UserValidationError(String message, Map<String, String> errorList) {
    super(message, errorList);
  }
}
