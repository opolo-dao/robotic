package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
                              HttpServletRequest req,
                              @Value("${SAVE_PATH}") String s) {
        String contextPath = req.getSession().getServletContext().getRealPath("/");
        contextPath = System.getenv("SAVE_PATH");
        Locale locale = LocaleContextHolder.getLocale();
        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        StringBuilder filePath = new StringBuilder();
        filePath.append(contextPath);
        filePath.append("/html/");
        filePath.append(competitionName);
        filePath.append("/");
        filePath.append(part);
        filePath.append("_");
        filePath.append(locale.getLanguage());
        filePath.append(".html");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath.toString());
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("cant't open file with rules");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("can't write to rules file");
        }


    }
}
