package lv.challenge.services.participant;

import lv.challenge.domain.competitors.Contact;
import lv.challenge.domain.competitors.Participant;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniil on 22.04.2017.
 */
@Component
public class ParticipantValidator implements Validator<Participant> {
    @Autowired
    Validator<Contact> contactValidator;

    @Override
    public void validate(Participant entity, Purpose purpose) throws ValidationError {
        Map<String, String> errorsList = new HashMap<>();
        Contact contact = entity.getContact();
        boolean validateContact = !(contact.getEmail().isEmpty() && contact.getPhoneNumber().isEmpty());
        if (validateContact) {
            try {
                contactValidator.validate(contact, Purpose.CREATE);
            } catch (ValidationError error) {
                errorsList.putAll(error.getErrorsMap());
                throw new ParticipantValidationError("Participant validation error", errorsList);
            }
        } else entity.setContact(null);
        if (!errorsList.isEmpty()) throw new ParticipantValidationError("Participant validation error", errorsList);
    }
}
