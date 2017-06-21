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
}
