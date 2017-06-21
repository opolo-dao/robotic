package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.competitors.Robot;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 12.05.2017.
 */
@Component
public class RobotDAO extends CompetitorsHibernateDAO<Robot> implements CompetitorsDAO<Robot>{

}
