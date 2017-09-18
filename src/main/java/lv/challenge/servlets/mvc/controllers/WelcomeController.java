package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Daniil on 06-May-17.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    TournamentService tournamentService;
    @Autowired
    ServletContext servletContext;
    @GetMapping
    public String getWelcome(Model model, HttpServletRequest req) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("home", "/html/home_" + locale.getLanguage() + ".html");
        List<Tournament> tournamentList = tournamentService.getAll();
        List<String> tournaments = tournamentList.stream()
                .map((tournament) -> tournament.getName().replace(" ", ""))
                .collect(Collectors.toList());
        model.addAttribute("tournaments", tournaments);
        Enumeration<String> enumeration = servletContext.getAttributeNames();
        return "home";
    }
}

