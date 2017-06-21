package lv.challenge.services.linefollower;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.LegoLinefollower;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoLinefollowerService extends CompetitionService<LegoLinefollower> {
    @Autowired
    public LegoLinefollowerService(CompetitionDAO<LegoLinefollower> dao) {
        this.dao = dao;
    }
}
