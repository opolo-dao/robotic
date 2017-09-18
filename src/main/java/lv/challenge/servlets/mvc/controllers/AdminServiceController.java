package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Daniil on 11.06.2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminServiceController {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    ApplicationService appService;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("robotsToCheckBadge", tournamentService.getNumberOfRobotsToCheck());
        model.addAttribute("tournament", appService.getActiveTournament());
        return "adminMenu";
    }

    @GetMapping("/createtournament")
    public String getCreateTournamentPage(Model model) {
        Tournament tournament = applicationContext.getBean(Tournament.class);
        model.addAttribute("tournament", tournament);
        return "createTournament";
    }

    @PostMapping("/createtournament")
    public String createTournament(@ModelAttribute Tournament tournament,
                                   BindingResult result) {
        tournamentService.save(tournament);
        tournamentService.setActiveTournament(tournament.getId());
        return "redirect:/admin#info";
    }

    @GetMapping("/checkrobots")
    public String getRobotsToCheck() {
        return "robotsToCheck";
    }

    @GetMapping("/tournamentsinfo")
    public String getTournamentsInfo(Model model) {
        model.addAttribute("tournamentsList", tournamentService.getAll());
        return "tournamentsInfo";
    }

    @GetMapping("/uploadpicture")
    public String uploadPicture() {
        return "uploadPicture";
    }

    @PostMapping("/upload")
    public String handleFormUpload(@RequestParam MultipartFile file,
                                   @RequestParam String name,
                                   HttpServletRequest req) {
        String originalFilename = file.getOriginalFilename();
        try (FileOutputStream fos = new FileOutputStream(appService.SAVE_PATH + File.separator + "/pictures/" + name + originalFilename.substring(originalFilename.lastIndexOf(".")))) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("cant upload picture");
        }
        return "adminMenu";
    }
}
