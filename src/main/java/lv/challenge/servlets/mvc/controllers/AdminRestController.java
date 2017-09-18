package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.services.common.HTMLStoreService;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    ApplicationService appService;
    @Autowired
    HTMLStoreService htmlStoreService;

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

    @PostMapping("/commentrobot")
    protected void commentRobot(@RequestParam int id,
                                @RequestParam String comment) {
        ((RobotService) robotService).setRobotComment(id, comment);
    }

    @GetMapping("/gettournamentstatistic")
    @ResponseBody
    protected Map<String, Object> getTournamentStatistic(
            @RequestParam Integer tournamentId) {
        return tournamentService.getTournamentStatistic(tournamentId);
    }

    @GetMapping("/setactivetournament")
    @ResponseStatus(HttpStatus.OK)
    protected void setActiveTournament(HttpServletResponse resp,
                                       @RequestParam Integer tournamentId) throws IOException {
        tournamentService.setActiveTournament(tournamentId);
    }

    @PostMapping("/updaterules")
    protected void updateRule(@RequestParam String part,
                              @RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        htmlStoreService.writeToHTMLFileStore(text, competitionName + File.separator + part, LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateaboutcompetitions")
    protected void updateAboutCompetition(@RequestParam String text,
                                          HttpServletRequest req) {
        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        htmlStoreService.writeToHTMLFileStore(text, "aboutCompetitions" + File.separator + competitionName, LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateabout")
    protected void updateAbout(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "about", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateagenda")
    protected void updateAgenda(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "agenda", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updatehome")
    protected void updateHome(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "home", LocaleContextHolder.getLocale());
    }
    @PostMapping("/updategeneralrules")
    protected void updateGeneralRules(@RequestParam String text) {

        htmlStoreService.writeToHTMLFileStore(text, "generalrules", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updatefooter")
    protected void updateFooter(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "footer", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateresults")
    protected void updateRule(@RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String[] paths = referer.split("/");
        String competitionName = paths[paths.length - 1];
        String tournamentId = paths[paths.length - 2];
        htmlStoreService.writeToHTMLFileStore(text, "results" + File.separator + tournamentId + "_" + competitionName, LocaleContextHolder.getLocale());
    }


}
