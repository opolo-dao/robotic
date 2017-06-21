package lv.challenge.services.folkrace;

import lv.challenge.domain.competitions.LegoFolkrace;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoFolkraceValidator implements Validator<LegoFolkrace> {
    @Override
    public void validate(LegoFolkrace entity, Purpose purpose) throws ValidationError {

    }
}
