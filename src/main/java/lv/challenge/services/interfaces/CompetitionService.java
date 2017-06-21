package lv.challenge.services.interfaces;

import lv.challenge.database.CompetitionDAO;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Daniil on 15.05.2017.
 */
@Transactional
public abstract class CompetitionService<T> {
    protected CompetitionDAO<T> dao;

    public List<List<String>> getParticipants() {
        return dao.getParticipantsList();
    }

    public void register(T entity) {

        dao.save(entity);
    }
    public void delete(T entity){

    }
}
