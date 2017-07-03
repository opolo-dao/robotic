package lv.challenge.database.hibernate.tournament;

import lv.challenge.database.TournamentDAO;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.competitors.Robot_;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.domain.tournament.Tournament_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * Created by Daniil on 24.06.2017.
 */
@Component
public class TournamentHibernateDAO implements TournamentDAO {
    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Tournament entity) {
        getCurrentSession().save(entity);

    }

    @Override
    public Optional<Tournament> loadById(int id) {
        return getCurrentSession().byId(Tournament.class).loadOptional(id);
    }

    @Override
    public void deleteById(int id) {
        getCurrentSession().delete(loadById(id));

    }

    @Override
    public void delete(Tournament tournament) {
        getCurrentSession().delete(tournament);

    }

    @Override
    public void update(Tournament tournament) {
        getCurrentSession().update(tournament);
    }

    @Override
    public List<Tournament> getAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tournament> query = builder.createQuery(Tournament.class);
        Root<Tournament> root = query.from(Tournament.class);
        query.select(root);
        TypedQuery<Tournament> typedQuery = getCurrentSession().createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public boolean isNameAlreadyExist(String name) {
        return getByName(name).isPresent();
    }

    @Override
    public Optional<Tournament> getByName(String name) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tournament> criteriaQuery = builder.createQuery(Tournament.class);
        Root<Tournament> tournamentRoot = criteriaQuery.from(Tournament.class);
        criteriaQuery.select(tournamentRoot);
        criteriaQuery.where(builder.equal(tournamentRoot.get(Tournament_.name), name));
        Tournament tournament;
        try {
            tournament = getCurrentSession().createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            tournament = null;
        }
        return Optional.ofNullable(tournament);
    }

    @Override
    public List<Robot> getAllRobots() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        cq.select(cq.from(Robot.class));
        cq.where(cb.equal(cq.from(Robot.class).get(Robot_.checked), true));
        TypedQuery<Robot> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<Robot> getCompetitionRobots(CompetitionType competition) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        Root<Robot> robotRoot = cq.from(Robot.class);
        cq.where(cb.isMember(competition, robotRoot.get(Robot_.competitions)))
                .where(cb.equal(robotRoot.get(Robot_.checked), true));
        Query query = getCurrentSession().createQuery(cq);
        return query.getResultList();
    }


}
