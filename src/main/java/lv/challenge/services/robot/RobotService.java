package lv.challenge.services.robot;

import lv.challenge.application.ApplicationService;
import lv.challenge.database.CompetitorsDAO;
import lv.challenge.database.hibernate.competitors.RobotDAO;
import lv.challenge.domain.competitions.Competition;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitions.FreeLinefollower;
import lv.challenge.domain.competitions.LegoLinefollower;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import lv.challenge.services.linefollower.FreeLinefollowerService;
import lv.challenge.services.linefollower.LegoLinefollowerService;
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
    FreeLinefollowerService freeLinefollowerService;
    @Autowired
    LegoLinefollowerService legoLinefollowerService;

    @Autowired
    public RobotService(CompetitorsDAO<Robot> competitorsDAO,
                        Validator<Robot> validator) {
        this.dao = competitorsDAO;
        this.validator = validator;
    }

    @Override
    public Optional<Robot> getByIdWithCollections(Integer id) {
        Optional<Robot> robotOptional = dao.getById(id);
        if (robotOptional.isPresent()) {
            Hibernate.initialize(robotOptional.get().getOperators());
            Hibernate.initialize(robotOptional.get().getCompetitions());
            Hibernate.initialize(robotOptional.get().getTournaments());
        }
        return robotOptional;
    }

    public void setRobotChecked(int id) {
        Optional<Robot> robotOptional = dao.getById(id);
        if (robotOptional.isPresent()) {
            robotOptional.get().setChecked(true);
            robotOptional.get().setAdminComment(null);
            robotOptional.get().getTournaments().add(appService.getActiveTournament());
            dao.update(robotOptional.get());
        }
    }

    public void setRobotComment(int id, String comment) {
        Optional<Robot> robotOptional = dao.getById(id);
        if (robotOptional.isPresent()) {
            robotOptional.get().setChecked(true);
            robotOptional.get().setAdminComment(comment);
            dao.update(robotOptional.get());
        }
    }

    public Optional<Robot> getByRegisteredNumber(int number, Tournament tournament) {
        return ((RobotDAO) dao).getRobotByNumber(number, tournament);
    }

    public boolean setRobotRegistrationNumber(Integer robotid, Integer registrationNumber, Tournament tournament) {
        Optional<Robot> robotById = dao.getById(robotid);
        Optional<Robot> robotByNumber = ((RobotDAO) dao).getRobotByNumber(registrationNumber, tournament);
        if (robotByNumber.isPresent() && robotById.isPresent() && !robotById.equals(robotByNumber)) return false;
        if (robotById.isPresent() && robotById.get().getTournaments().contains(tournament)) {
            robotById.get().setRegistered_number(registrationNumber);
            dao.update(robotById.get());
            return true;
        } else
            return false;

    }

    public Competition registerLfRobotAttempt(Integer registeredNumber, Double time, Tournament tournament) {
        String check = checkLfRobot(registeredNumber, tournament);
        if (!check.equals("OK")) return null;
        Robot robot = ((RobotDAO) dao).getRobotByNumber(registeredNumber, tournament).get();
        if (robot.getCompetitions().contains(CompetitionType.freeLinefollower)) {
            FreeLinefollower record = new FreeLinefollower();
            record.setSTime(time);
            record.setRobot(robot);
            record.setTournament(tournament);
            freeLinefollowerService.register(record);
            return record;
        }
        if (robot.getCompetitions().contains(CompetitionType.legoLinefollower)) {
            LegoLinefollower record = new LegoLinefollower();
            record.setSTime(time);
            record.setRobot(robot);
            record.setTournament(tournament);
            legoLinefollowerService.register(record);
            return record;
        }
        return null;
    }

    public String checkLfRobot(int number, Tournament tournament) {
        Optional<Robot> optRobot = ((RobotDAO) dao).getRobotByNumber(number, tournament);
        if (!optRobot.isPresent()) return "Robot with this number not exists";
        Robot robot = optRobot.get();
        String result = "Robot does not registered to LineFollower competitions";
        if (robot.getCompetitions().contains(CompetitionType.freeLinefollower)) {
            result = "OK";
            if (freeLinefollowerService.getRobotAttempts(robot, tournament).size() >= appService.getActiveTournament().getFreeLinefollowerSettings().getNumberOfAttempts()) {
                return "Max number of attempts reached";
            }
        }
        if (robot.getCompetitions().contains(CompetitionType.legoLinefollower)) {
            result = "OK";
            if (legoLinefollowerService.getRobotAttempts(robot, tournament).size() >= appService.getActiveTournament().getLegoLinefollowerSettings().getNumberOfAttempts()) {
                return "Max number of attempts reached";
            }
        }
        return result;
    }
}
