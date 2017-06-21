package lv.challenge.services.labyrinth;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.LegoLabyrinth;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class LegoLabyrinthService extends CompetitionService<LegoLabyrinth> {
@Autowired
    public LegoLabyrinthService(CompetitionDAO<LegoLabyrinth> dao) {
    this.dao = dao;
    }
}
