package lv.challenge.database.hibernate.competitions;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.database.hibernate.CompetitionsHibernateDAO;
import lv.challenge.domain.competitions.MiniSumo;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 21.05.2017.
 */
@Component
public class MiniSumoDAO extends CompetitionsHibernateDAO<MiniSumo> implements CompetitionDAO<MiniSumo> {
}
