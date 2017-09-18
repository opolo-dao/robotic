package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.competitors.Team;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/operator/registrar")
public class RegistrarController {
    @Autowired
    TeamService teamService;
    @Autowired
    RobotService robotService;
    @Autowired
    ApplicationService appService;

    @GetMapping
    public String getRegistrarPage(Model model) {
        List<Team> teams = teamService.getAll();
        model.addAttribute("teams", teams);
        return "registrarPage";
    }

    @GetMapping("/getteamrobots")
    @ResponseBody
    public List<Robot> getRobots(@RequestParam Integer teamid) {
        return robotService.getAllByTeamId(teamid);
    }

    @PostMapping("/setrobotnumber")
    @ResponseBody
    public void setRobotNumber(@RequestParam Integer id,
                               @RequestParam Integer number,
                               HttpServletResponse resp) {
        boolean success = robotService.setRobotRegistrationNumber(id, number, appService.getActiveTournament());
        if (success) return;
        else resp.setStatus(HttpServletResponse.SC_CONFLICT);
    }
}
