package lv.challenge.services.labyrinth;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.FreeLabyrinth;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class FreeLabyrinthService extends CompetitionService<FreeLabyrinth> {
@Autowired

    public FreeLabyrinthService(CompetitionDAO<FreeLabyrinth> dao) {
    this.dao = dao;
    }
}
