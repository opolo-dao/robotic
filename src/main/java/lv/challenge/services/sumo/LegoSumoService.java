package lv.challenge.services.sumo;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.LegoSumo;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 31.05.2017.
 */
@Component
public class LegoSumoService extends CompetitionService<LegoSumo> {
    @Autowired
    public LegoSumoService(CompetitionDAO<LegoSumo> dao) {
        this.dao = dao;
    }
}
