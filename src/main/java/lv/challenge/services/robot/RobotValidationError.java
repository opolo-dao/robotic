package lv.challenge.services.robot;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
public class RobotValidationError extends ValidationError {
  public RobotValidationError(String message, Map<String, String> errorList) {
    super(message, errorList);
  }
}
