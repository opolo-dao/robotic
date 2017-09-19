package lv.challenge.services.team;

import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Participant;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.competitors.Team;
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
public class TeamService extends CompetitorService<Team> {
 @Autowired
 public TeamService(CompetitorsDAO<Team> competitorsDAO,
                    Validator<Team> validator){
 this.dao = competitorsDAO;
 this.validator = validator;
 }

 @Override
 public Optional<Team> getByIdWithCollections(Integer id) {
  Optional<Team> team = getById(id);
  if(team.isPresent()){
   Hibernate.initialize(team.get().getRobots());
   Hibernate.initialize(team.get().getParticipants());
   for(Participant participant: team.get().getParticipants()){
    Hibernate.initialize(participant.getContact());
   }
   for(Robot robot: team.get().getRobots()){
    Hibernate.initialize(robot.getOperators());
   Hibernate.initialize(robot.getCompetitions());
       Hibernate.initialize(robot.getTournaments());
   }
  }
  return team;
 }
}
