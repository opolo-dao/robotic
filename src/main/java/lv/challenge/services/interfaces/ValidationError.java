package lv.challenge.services.interfaces;

import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
public abstract class ValidationError extends Exception {
  private Map<String, String> errorsMap;
public ValidationError(String message, Map<String, String> errorList){
  this.errorsMap = errorList;
}
  public Map<String, String> getErrorsMap() {
    return errorsMap;
  }
}
