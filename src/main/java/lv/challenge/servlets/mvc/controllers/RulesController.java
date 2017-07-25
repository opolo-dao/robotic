package lv.challenge.servlets.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Daniil on 01.06.2017.
 */
@Controller
@RequestMapping("/rules")
public class RulesController {
@GetMapping("/sumo")
    public String getSumoRules(Model model){
    return "sumoRules";
}

    @GetMapping("/linefollower")
    public String getLinefollowerRules(Model model) {
        return "linefollowerRules";
    }

    @GetMapping("/labyrinth")
    public String getLabyrinthRules(Model model) {
        return "labyrinthRules";
    }

    @GetMapping("/folkrace")
    public String getFolkraceRules(Model model) {
        return "folkraceRules";
    }
}
