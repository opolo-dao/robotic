package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.competitors.Team;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 11.05.2017.
 */
@Component
public class TeamDAO extends CompetitorsHibernateDAO<Team> implements CompetitorsDAO<Team>{
}
