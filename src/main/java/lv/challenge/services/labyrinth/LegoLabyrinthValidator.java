package lv.challenge.services.labyrinth;

import lv.challenge.domain.competitions.LegoLabyrinth;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoLabyrinthValidator implements Validator<LegoLabyrinth> {
    @Override
    public void validate(LegoLabyrinth entity, Purpose purpose) throws ValidationError {

    }
}
