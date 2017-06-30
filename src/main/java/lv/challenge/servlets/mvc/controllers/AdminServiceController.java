package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Created by Daniil on 11.06.2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminServiceController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping
    public String getAdminPage(Model model) {
        return "adminMenu";
    }

    @GetMapping("/createtournament")
    public String getCreateTournamentPage(Model model) {
        return "createTournament";
    }

    @PostMapping("/createtournament")
    public String createTournament(@RequestParam("name") String name,
                                   @RequestParam("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDateTime,
                                   @RequestParam("startRegistration") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startRegistration,
                                   @RequestParam("endRegistration") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endRegistration
    ) {
        Tournament tournament = Tournament.TournamentBuilder.createTournament()
                .withActive(true)
                .withEventDateTime(eventDateTime)
                .withEndRegistrationDateTime(endRegistration)
                .withStartRegistrationDateTime(startRegistration)
                .withName(name)
                .build();
        tournamentService.save(tournament);
        return "redirect:/admin#info";
    }
}
