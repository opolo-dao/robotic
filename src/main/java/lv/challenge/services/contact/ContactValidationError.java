package lv.challenge.services.contact;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
public class ContactValidationError extends ValidationError {
  public ContactValidationError(String message, Map<String, String> errorList) {
    super(message, errorList);
  }
}
