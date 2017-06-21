package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.users.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * Created by Daniil on 12.05.2017.
 */
@Repository
public class UserDAO extends CompetitorsHibernateDAO<User> implements CompetitorsDAO<User> {
    public Optional<User> findByLogin(String login){
      User user = null;
        String hql = "SELECT user FROM User user WHERE user.login=:login";
      TypedQuery<User> query =  getCurrentSession().createQuery(hql);
      query.setParameter("login",login);
      try{
          user = query.getSingleResult();
      }
      catch (NoResultException e){
          user = null;
      }
      return Optional.ofNullable(user);
    }

}
