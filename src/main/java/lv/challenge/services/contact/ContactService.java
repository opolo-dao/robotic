package lv.challenge.services.contact;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Contact;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Daniil on 24.04.2017.
 */
@Component
public class ContactService extends CompetitorService<Contact> {
 @Autowired
  public ContactService(CompetitorsDAO<Contact> competitorsDAO,
                        Validator<Contact> validator) {
    this.dao = competitorsDAO;
    this.validator = validator;
  }

    @Override
    public Optional<Contact> getByIdWithCollections(Integer id) {
        return null;
    }
}
