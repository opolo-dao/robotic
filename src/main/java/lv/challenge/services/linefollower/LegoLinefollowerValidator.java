package lv.challenge.services.linefollower;

import lv.challenge.domain.competitions.LegoLinefollower;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoLinefollowerValidator implements Validator<LegoLinefollower> {
    @Override
    public void validate(LegoLinefollower entity, Purpose purpose) throws ValidationError {

    }
}
