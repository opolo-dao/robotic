package lv.challenge.services.folkrace;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.LegoFolkrace;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoFolkraceService extends CompetitionService<LegoFolkrace> {
@Autowired

    public LegoFolkraceService(CompetitionDAO<LegoFolkrace> dao) {
    this.dao = dao;
    }
}
