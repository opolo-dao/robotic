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
@RequestMapping("/results")
public class ResultsController {
    private Set<String> competitionsList = new HashSet<String>() {{
        add("sumo");
        add("linefollower");
        add("folkrace");
        add("labyrinth");
    }};

    @GetMapping("/{tournament}/{competition}")
    public String getResult(@PathVariable String competition,
                            @PathVariable String tournament,
                            Model model,
                            HttpServletResponse resp) {
        if (!competitionsList.contains(competition)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "home";
        }
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("competition", "/html/results/" + tournament + "_" + competition + "_" + locale.getLanguage() + ".html");
        return "results";
    }
}
