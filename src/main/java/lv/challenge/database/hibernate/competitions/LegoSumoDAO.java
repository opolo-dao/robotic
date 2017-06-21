package lv.challenge.database.hibernate.competitions;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.database.hibernate.CompetitionsHibernateDAO;
import lv.challenge.domain.competitions.LegoSumo;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 31.05.2017.
 */
@Component
public class LegoSumoDAO extends CompetitionsHibernateDAO<LegoSumo> implements CompetitionDAO<LegoSumo> {
}
