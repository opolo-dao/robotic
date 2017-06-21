package lv.challenge.services.folkrace;

import lv.challenge.domain.competitions.FreeFolkrace;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class FreeFolkraceValidator implements Validator<FreeFolkrace> {
    @Override
    public void validate(FreeFolkrace entity, Purpose purpose) throws ValidationError {

    }
}
