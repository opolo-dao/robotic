package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.competitors.Participant;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 12.05.2017.
 */
@Component
public class ParticipantDAO extends CompetitorsHibernateDAO<Participant> implements CompetitorsDAO<Participant> {
    @Override
    public boolean isNameAlreadyExist(String name) {
        return false;
    }
}
