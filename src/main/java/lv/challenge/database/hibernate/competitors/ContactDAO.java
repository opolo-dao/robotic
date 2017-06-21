package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.competitors.Contact;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 12.05.2017.
 */
@Component
public class ContactDAO extends CompetitorsHibernateDAO<Contact> implements CompetitorsDAO<Contact> {
    @Override
    public boolean isNameAlreadyExist(String name) {
        return false;
    }
}
