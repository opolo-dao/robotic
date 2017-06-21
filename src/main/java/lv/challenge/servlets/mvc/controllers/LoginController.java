package lv.challenge.servlets.mvc.controllers;

import lv.challenge.services.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TeamService service;

    @GetMapping
    public String getLoginPage(HttpServletRequest req,
                            HttpSession session) {
        return "login";

    }

/*    @PostMapping
    public String validateTeam(@RequestParam String teamName,
                               @RequestParam String password,
                               HttpServletRequest req) {
        Optional<Integer> teamid = service.authorize(teamName, password);
        if (teamid.isPresent()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("teamid", teamid.get().intValue());
            session.setAttribute("authorized", true);
            return "redirect:menu";
        }
        return "login";
    }*/
}
