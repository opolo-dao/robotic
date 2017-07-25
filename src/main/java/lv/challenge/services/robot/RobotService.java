package lv.challenge.services.robot;

import lv.challenge.application.ApplicationService;
import lv.challenge.database.CompetitorsDAO;
import lv.challenge.domain.competitors.Robot;
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
public class RobotService extends CompetitorService<Robot> {
    @Autowired
    ApplicationService appService;
@Autowired
  public RobotService(CompetitorsDAO<Robot> competitorsDAO,
                      Validator<Robot> validator) {
this.dao = competitorsDAO;
this.validator = validator;
  }

  @Override
  public Optional<Robot> getByIdWithCollections(Integer id) {
    Optional<Robot> robotOptional = dao.getById(id);
    if(robotOptional.isPresent()){
      Hibernate.initialize(robotOptional.get().getOperators());
      Hibernate.initialize(robotOptional.get().getCompetitions());
    }
    return robotOptional;
  }

    public void setRobotChecked(int id) {
        Optional<Robot> robotOptional = dao.getById(id);
        if (robotOptional.isPresent()) {
            robotOptional.get().setChecked(true);
            robotOptional.get().setTournamentId(appService.getActiveTournament().getId());
            dao.update(robotOptional.get());
        }
    }
}
