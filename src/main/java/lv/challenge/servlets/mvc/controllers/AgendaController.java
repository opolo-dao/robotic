package lv.challenge.servlets.mvc.controllers;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/agenda")
public class AgendaController {
    @GetMapping
    public String getAgenda(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("agenda", "/html/agenda_" + locale.getLanguage() + ".html");
        return "agenda";
    }
}
