package lv.challenge.database.hibernate;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    public void deleteAllTournamentRecords(Tournament tournament) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(competitionClass);
        Root<T> root = cd.from(competitionClass);
        cd.where(cb.equal(root.get("tournament"), tournament));
        getCurrentSession().createQuery(cd).executeUpdate();
    }

    public Optional<T> getById(int id) {
        return Optional.ofNullable(getCurrentSession().get(competitionClass, id));
    }

    public Optional<T> loadById(int id) {
        return getCurrentSession().byId(competitionClass).loadOptional(id);
    }

    public List<T> getAll() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(competitionClass);
        Root<T> root = cq.from(competitionClass);
        cq.select(root);
        return getCurrentSession().createQuery(cq).getResultList();

    }

    public List<T> getRobotResults(Robot robot, Tournament tournament) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(competitionClass);
        Root<T> root = cq.from(competitionClass);
        cq.select(root);
        Predicate predicate = cb.and(cb.equal(root.get("robot"), robot),
                cb.equal(root.get("tournament"), tournament));
        cq.where(predicate);
        TypedQuery<T> tq = getCurrentSession().createQuery(cq);
        return tq.getResultList();
    }
}
