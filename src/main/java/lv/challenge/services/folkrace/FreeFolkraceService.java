package lv.challenge.services.folkrace;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.FreeFolkrace;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class FreeFolkraceService extends CompetitionService<FreeFolkrace> {
    @Autowired
    public FreeFolkraceService(CompetitionDAO<FreeFolkrace> dao) {
        this.dao = dao;
    }
}
