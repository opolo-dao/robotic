package lv.challenge.servlets.mvc.controllers;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public String getAboutUsPage(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("about", "/html/about_" + locale.getLanguage() + ".html");
        return "about";
    }

    @GetMapping("/competitions")
    public String getAboutCompetiitons(Model model) {
        return "aboutCompetitions";
    }

    @GetMapping("/sumo")
    public String getAboutSumo(Model model) {
        return "aboutSumo";
    }

    @GetMapping("/linefollower")
    public String getAboutLinefollower(Model model) {
        return "aboutLinefollower";
    }

    @GetMapping("/folkrace")
    public String getAboutFolkrace(Model model) {
        return "aboutFolkrace";
    }

    @GetMapping("/labyrinth")
    public String getAboutLabyrinth(Model model) {
        return "aboutLabyrinth";
    }
}
