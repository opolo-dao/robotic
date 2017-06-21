package lv.challenge.services.participant;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
public class ParticipantValidationError extends ValidationError {
  public ParticipantValidationError(String message, Map<String, String> errorList) {
    super(message, errorList);
  }
}
