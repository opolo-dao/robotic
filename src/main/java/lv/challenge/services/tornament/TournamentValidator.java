package lv.challenge.services.tornament;

import lv.challenge.database.TournamentDAO;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.interfaces.ValidationError;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniil on 24.06.2017.
 */
@Component
public class TournamentValidator implements Validator<Tournament> {
    @Autowired
    TournamentDAO dao;

    @Override
    public void validate(Tournament tournament, Purpose purpose) throws ValidationError {
        Map<String, String> errors = new HashMap<>();
        if (purpose == Purpose.CREATE) {
            if (dao.isNameAlreadyExist(tournament.getName())) {
                errors.put("tournamentNameErrorMsg", "This tournament name already exist");
            }
        }
        if (purpose == Purpose.UPDATE) {

        }

    }
}
