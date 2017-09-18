package lv.challenge.database.hibernate.tournament;

import lv.challenge.database.TournamentDAO;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.*;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.domain.tournament.Tournament_;
import lv.challenge.domain.users.User;
import lv.challenge.domain.users.User_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
    public Optional<Tournament> loadById(Integer id) {
        return getCurrentSession().byId(Tournament.class).loadOptional(id);
    }

    @Override
    public void deleteById(Integer id) {
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
    public List<Robot> getAllRobots(Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        Root<Robot> root = cq.from(Robot.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Robot_.tournamentId), tournamentId));
        TypedQuery<Robot> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public List<Robot> getCompetitionRobots(CompetitionType competition, Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        Root<Robot> robotRoot = cq.from(Robot.class);
        cq.where(cb.and(cb.isMember(competition, robotRoot.get(Robot_.competitions)), cb.equal(robotRoot.get(Robot_.tournamentId), tournamentId)));
        Query query = getCurrentSession().createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Long getCompetitionRobotsCount(CompetitionType competition, Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Robot> robotRoot = cq.from(Robot.class);
        cq.select(cb.countDistinct(robotRoot));
        cq.where(cb.and(cb.isMember(competition, robotRoot.get(Robot_.competitions)), cb.equal(robotRoot.get(Robot_.checked), true), cb.equal(robotRoot.get(Robot_.tournamentId), tournamentId)));
        TypedQuery<Long> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Robot> getRobotsToAccept() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Robot> cq = cb.createQuery(Robot.class);
        Root<Robot> root = cq.from(Robot.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Robot_.checked), false));
        TypedQuery<Robot> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getResultList();
    }

    public Long getNumberOfRobotsToCheck() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Robot> root = cq.from(Robot.class);
        cq.select(cb.count(root));
        cq.where(cb.equal(root.get(Robot_.checked), false));
        TypedQuery<Long> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getSingleResult();
    }

    public List<String> getUniqCountries(Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Robot> robotRoot = cq.from(Robot.class);
        Join<Team, User> teamUserJoin = robotRoot.join(Robot_.team).join(Team_.user);
        cq.select(teamUserJoin.get(User_.state)).distinct(true);
        cq.where(cb.equal(robotRoot.get(Robot_.tournamentId), tournamentId));
        TypedQuery<String> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public Long getRobotsCount(Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Robot> robotRoot = cq.from(Robot.class);
        cq.select(cb.count(robotRoot));
        cq.where(cb.equal(robotRoot.get(Robot_.tournamentId), tournamentId));
        TypedQuery<Long> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getSingleResult();
    }

    @Override
    public Long getParticipantsCount(Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Participant> root = cq.from(Participant.class);
        Join<Participant, Robot> jpr = root.join(Participant_.robots);
        cq.select(cb.countDistinct(root));
        cq.where(cb.equal(jpr.get(Robot_.tournamentId), tournamentId));
        TypedQuery<Long> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getSingleResult();
    }

    public Long getTeamsCount(Integer tournamentId) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Team> teamRoot = cq.from(Team.class);
        Join<Team, Robot> teamRobotJoin = teamRoot.join(Team_.robots);
        cq.select(cb.countDistinct(teamRoot));
        cq.where(cb.equal(teamRobotJoin.get(Robot_.tournamentId), tournamentId));
        TypedQuery<Long> typedQuery = getCurrentSession().createQuery(cq);
        return typedQuery.getSingleResult();
    }

    @Override
    public Optional<Tournament> getActiveTournament() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tournament> cq = cb.createQuery(Tournament.class);
        Root<Tournament> root = cq.from(Tournament.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Tournament_.active), true));
        TypedQuery<Tournament> query = getCurrentSession().createQuery(cq);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }
    }

}
