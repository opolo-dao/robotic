package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

    @GetMapping("/gettournamentstatistic")
    @ResponseBody
    protected Map<String, List<String>> getTournamentStatistic(
            @RequestParam Integer tournamentId) {
        return tournamentService.getTournamentStatistic(tournamentId);
    }

    @PostMapping("/updaterules")
    protected void updateRule(@RequestParam String part,
                              @RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        writeToHTMLFileStore(text, competitionName + File.separator + part);
    }

    @PostMapping("/updateabout")
    protected void updateAbout(@RequestParam String text) {
        writeToHTMLFileStore(text, "about");
    }

    @PostMapping("/updateagenda")
    protected void updateAgenda(@RequestParam String text) {
        writeToHTMLFileStore(text, "agenda");
    }

    @PostMapping("/updategeneralrules")
    protected void updateGeneralRules(@RequestParam String text) {

        writeToHTMLFileStore(text, "generalrules");
    }

    @PostMapping("/updatematchtable")
    protected void updateRule(@RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        writeToHTMLFileStore(text, "matchtables" + File.separator + competitionName);
    }

    private void writeToHTMLFileStore(String data, String filename) {
        Locale locale = LocaleContextHolder.getLocale();
        StringBuilder file = new StringBuilder();
        file.append(appService.SAVE_PATH);
        file.append(File.separator);
        file.append("html");
        file.append(File.separator);
        file.append(filename);
        file.append("_");
        file.append(locale.getLanguage());
        file.append(".html");
        try (FileOutputStream fos = new FileOutputStream(file.toString())) {
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Cant open html file to rewrite");
        } catch (IOException e) {
            System.out.println("Can't write to html file");
        }
    }
}
