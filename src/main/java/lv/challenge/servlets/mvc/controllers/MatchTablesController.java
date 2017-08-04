package lv.challenge.servlets.mvc.controllers;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


@Controller
@RequestMapping("/tables")
public class MatchTablesController {
    private Set<String> competitionsList = new HashSet<String>() {{
        add("sumo");
        add("linefollower");
        add("folkrace");
        add("labyrinth");
    }};

    @GetMapping("/{competition}")
    public String getSumoTable(@PathVariable String competition, Model model, HttpServletResponse resp) {
        if (!competitionsList.contains(competition)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "home";
        }
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("competition", "/html/matchtables/" + competition + "_" + locale.getLanguage() + ".html");
        return "matchTable";
    }
}
