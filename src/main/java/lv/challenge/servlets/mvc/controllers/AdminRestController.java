package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 17.07.2017.
 */
@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    @Autowired
    TournamentService tournamentService;
    @Autowired
    CompetitorService<Robot> robotService;

    @GetMapping("/robotstochecklist")
    @ResponseBody
    protected Map<String, List<List<String>>> getRobotsToCheckList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getRobotsToAccept());
        return map;
    }

    @PostMapping("/acceptrobot")
    protected void acceptRobot(@RequestParam int id) {
        ((RobotService) robotService).setRobotChecked(id);
    }
}
