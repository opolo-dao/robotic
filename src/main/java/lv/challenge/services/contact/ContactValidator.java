package lv.challenge.services.contact;

import lv.challenge.domain.competitors.Contact;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniil on 21.04.2017.
 */
@Component
public class ContactValidator implements Validator<Contact> {
  @Override
  public void validate(Contact entity, Purpose purpose) throws ValidationError {
    Map<String, String> errorsList = new HashMap<>();

//    if(!entity.getPhoneNumber().matches("\\d+")) errorsList.put("phone","Invalid phone number");
    if(!entity.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
      errorsList.put("contactEmailErrorMsg","Invalid email");
  }
    if(errorsList.size() != 0) throw new ContactValidationError("Contact validation error", errorsList);
  }
}
