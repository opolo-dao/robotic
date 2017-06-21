package lv.challenge.services.participant;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Contact;
import lv.challenge.domain.competitors.Participant;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Daniil on 24.04.2017.
 */
@Component
public class ParticipantService extends CompetitorService<Participant> {
    @Autowired
    public ParticipantService(CompetitorsDAO<Participant> competitorsDAO,
                              Validator<Participant> validator) {
        this.dao = competitorsDAO;
        this.validator = validator;
    }

    @Autowired
    CompetitorsDAO<Contact> contactDao;
    @Autowired
    Validator<Contact> contactValidator;

    @Override
    public Optional<Participant> getByIdWithCollections(Integer id) {
        Optional<Participant> participantOptional = dao.loadById(id);
        if (participantOptional.isPresent()) {
            Hibernate.initialize(participantOptional.get().getContact());
        }
        return participantOptional;
    }
}
