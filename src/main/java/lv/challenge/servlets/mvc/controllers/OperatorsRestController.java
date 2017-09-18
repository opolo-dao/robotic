package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.competitions.Competition;
import lv.challenge.domain.competitions.FreeLinefollower;
import lv.challenge.domain.competitions.LegoLinefollower;
import lv.challenge.services.linefollower.FreeLinefollowerService;
import lv.challenge.services.linefollower.LegoLinefollowerService;
import lv.challenge.services.robot.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operator/rest")
public class OperatorsRestController {
    @Autowired
    LegoLinefollowerService legoLinefollowerService;
    @Autowired
    FreeLinefollowerService freeLinefollowerService;
    @Autowired
    RobotService robotService;
    @Autowired
    ApplicationService appService;

    @GetMapping("/lf/table")
    @ResponseBody
    protected Map<String, List<List<String>>> getLfResultsTable() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", freeLinefollowerService.getAllAsStringArray());
        return map;
    }

    @GetMapping("/lf/checknumber")
    @ResponseBody

    protected String chechNumber(@RequestParam int number,
                                 HttpServletResponse resp) {
        String str = robotService.checkLfRobot(number, appService.getActiveTournament());
        if (str.equals("OK")) return "Robot is found";
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return str;
    }

    @PostMapping("/lf/register")
    protected List<String> registerLfAttempt(@RequestParam int number,
                                             @RequestParam double time,
                                             HttpServletResponse resp) {
        Competition regInfo = robotService.registerLfRobotAttempt(number, time, appService.getActiveTournament());
        if (regInfo == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ArrayList<String>() {{
                add("See problem on robot registration number");
            }};

        }
        if (regInfo instanceof FreeLinefollower) {
            return prepareRecord((FreeLinefollower) regInfo);
        }
        if (regInfo instanceof LegoLinefollower) {
            return prepareRecord((LegoLinefollower) regInfo);
        }
        return null;
    }

    private List<String> prepareRecord(FreeLinefollower record) {
        List<String> list = new ArrayList<>();
        list.add(record.getRobot().getName());
        list.add(String.valueOf(record.getRobot().getRegistered_number()));
        list.add(String.valueOf(record.getSTime()));
        list.add(record.getDate().toString());
        return list;
    }

    private List<String> prepareRecord(LegoLinefollower record) {
        List<String> list = new ArrayList<>();
        list.add(record.getRobot().getName());
        list.add(String.valueOf(record.getRobot().getRegistered_number()));
        list.add(String.valueOf(record.getSTime()));
        list.add(record.getDate().toString());
        return list;
    }
}
