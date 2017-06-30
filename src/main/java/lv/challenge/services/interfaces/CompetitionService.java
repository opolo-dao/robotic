package lv.challenge.services.interfaces;

import lv.challenge.database.CompetitionDAO;

import javax.transaction.Transactional;

/**
 * Created by Daniil on 15.05.2017.
 */
@Transactional
public abstract class CompetitionService<T> {
    protected CompetitionDAO<T> dao;


    public void register(T entity) {

        dao.save(entity);
    }
    public void delete(T entity){

    }
}
