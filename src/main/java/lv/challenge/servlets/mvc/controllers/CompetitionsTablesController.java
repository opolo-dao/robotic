package lv.challenge.servlets.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Daniil on 28.06.2017.
 */
@Controller
@RequestMapping("/participants")
public class CompetitionsTablesController {
    @GetMapping
    public String getSumoList() {
        return "participantsTables";
    }
}
