/*
package lv.challenge.services.interfaces;

import lv.challenge.domain.competitions.Competition;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

*/
/**
 * Created by Daniil on 31.05.2017.
 *//*

@Component
public class TournamentServices {
    @Autowired
    ApplicationContext applicationContext;
    public void registerRobotOnCompetitions(Robot robot) {
        for(CompetitionType type: robot.getCompetitions()){
            CompetitionService service = (CompetitionService) applicationContext.getBean(type.toString()+"Service");
            Competition record = (Competition) applicationContext.getBean(type.toString());
            record.setRobot(robot);
            service.register(record);
        }
    }
    public void unRegisterRobotFromCompetitions(Robot robot) {
        for(CompetitionType type: robot.getCompetitions()){


        }
    }
}
*/
