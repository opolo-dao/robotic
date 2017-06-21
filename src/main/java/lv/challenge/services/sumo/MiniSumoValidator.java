package lv.challenge.services.sumo;

import lv.challenge.domain.competitions.MiniSumo;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 31.05.2017.
 */
@Component
public class MiniSumoValidator implements Validator<MiniSumo> {
    @Override
    public void validate(MiniSumo entity, Purpose purpose) throws ValidationError {

    }
}
