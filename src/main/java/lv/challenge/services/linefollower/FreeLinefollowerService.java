package lv.challenge.services.linefollower;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.FreeLinefollower;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class FreeLinefollowerService extends CompetitionService<FreeLinefollower> {
    @Autowired
    public FreeLinefollowerService(CompetitionDAO<FreeLinefollower> dao) {
    this.dao = dao;
    }
}
