package lv.challenge.database.hibernate;

import lv.challenge.database.DBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Daniil on 11.05.2017.
 */
public abstract class CompetitorsHibernateDAO<T> {
    private Class<T> objectClass;

    public CompetitorsHibernateDAO() {
        this.objectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T save(T entity) throws DBException {
        getCurrentSession().persist(entity);
        return entity;
    }


    public Optional<T> getById(int id) {
        return Optional.ofNullable(getCurrentSession().get(objectClass,id));
    }
    public Optional<T> loadById(int id){
        return  getCurrentSession().byId(objectClass).loadOptional(id);
    }

    public Optional<T> getByName(String name) {
        String hql = "SELECT " + objectClass + " t WHERE t.name = : name";
        T entity = (T) getCurrentSession().createQuery(hql).getResultList().get(0);
        return Optional.ofNullable(entity);
    }

    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        T type = (T) session.get(objectClass, id);
        session.delete(type);
    }

    public Set<T> getByMultipleIds(List<Integer> ids) {
        return new HashSet<>(getCurrentSession().byMultipleIds(objectClass).multiLoad(ids));
    }

    public void update(T entity) {
        Session s = getCurrentSession();
        // s.clear();
        s.update(entity);
    }


    public List<T> getAll() {
        return null;
    }


    public List<T> getByTeamId(Integer id) {
        String hql = "SELECT t FROM " +
                objectClass.getSimpleName() + " t " +
                "WHERE t.team.id = :id";
        TypedQuery<T> query = getCurrentSession().createQuery(hql, objectClass).setParameter("id", id);
        return query.getResultList();
    }


    public boolean isNameAlreadyExist(String name) {
        String hql = "SELECT t FROM " +
                objectClass.getSimpleName() + " t " +
                "WHERE t.name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return !query.list().isEmpty();
    }

    public List<T> getByFieldValue(String fieldName, String value) {
        String hql = "SELECT t FROM " +
                objectClass.getSimpleName() + " t " +
                "WHERE t." + fieldName + " = :value";
        TypedQuery<T> query = getCurrentSession().createQuery(hql, objectClass).setParameter("value", value);
        return query.getResultList();
    }

    public void removeFromSessionCache(T entity) {
        getCurrentSession().evict(entity);
    }
}
