package lv.challenge.database.hibernate.competitions;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.database.hibernate.CompetitionsHibernateDAO;
import lv.challenge.domain.competitions.LegoLinefollower;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 31.05.2017.
 */
@Component
public class LegoLinefollowerDAO extends CompetitionsHibernateDAO<LegoLinefollower> implements CompetitionDAO<LegoLinefollower> {
}
