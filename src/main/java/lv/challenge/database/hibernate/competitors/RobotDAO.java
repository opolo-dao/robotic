package lv.challenge.database.hibernate.competitors;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.CompetitorsHibernateDAO;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.competitors.Robot_;
import lv.challenge.domain.tournament.Tournament;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * Created by Daniil on 12.05.2017.
 */
@Component
public class RobotDAO extends CompetitorsHibernateDAO<Robot> implements CompetitorsDAO<Robot>{
    public Optional<Robot> getRobotByNumber(int number, Tournament tournament) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        Root<Robot> root = cq.from(Robot.class);
        cq.select(root);
        Predicate predicate = cb.and(cb.equal(root.get(Robot_.registeredNumber), number),
                cb.equal(root.get(Robot_.tournamentId), tournament.getId()));
        cq.where(predicate);
        TypedQuery<Robot> tq = getCurrentSession().createQuery(cq);
        try {
            return Optional.ofNullable(tq.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
