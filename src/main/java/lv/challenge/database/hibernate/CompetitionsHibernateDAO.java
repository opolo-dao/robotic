package lv.challenge.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Created by Daniil on 21.05.2017.
 */
public abstract class CompetitionsHibernateDAO<T> {
    private Class<T> competitionClass;

    public CompetitionsHibernateDAO() {
        this.competitionClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getCurrentSession().persist(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }
    public Optional<T> getById(int id) {
        return Optional.ofNullable(getCurrentSession().get(competitionClass,id));
    }
    public Optional<T> loadById(int id){
        return  getCurrentSession().byId(competitionClass).loadOptional(id);
    }

    public List<List<String>> getParticipantsList() {
        return null;
    }
}
