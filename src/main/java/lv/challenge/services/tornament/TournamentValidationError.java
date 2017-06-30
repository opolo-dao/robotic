package lv.challenge.services.tornament;

import lv.challenge.services.interfaces.ValidationError;

import java.util.Map;

/**
 * Created by Daniil on 24.06.2017.
 */
public class TournamentValidationError extends ValidationError {
    public TournamentValidationError(String message, Map<String, String> errorList) {
        super(message, errorList);
    }
}

