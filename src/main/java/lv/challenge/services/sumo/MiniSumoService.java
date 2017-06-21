package lv.challenge.services.sumo;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.MiniSumo;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 15.05.2017.
 */
@Component
public class MiniSumoService extends CompetitionService<MiniSumo> {
    @Autowired
    public MiniSumoService(CompetitionDAO<MiniSumo> dao) {
        this.dao = dao;
    }
}
