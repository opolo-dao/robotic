package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.photo_video.Photo;
import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.photo_video.PhotoService;
import lv.challenge.services.photo_video.VideoService;
import lv.challenge.services.tornament.TournamentService;
import lv.challenge.servlets.mailService.MailTemplates;
import lv.challenge.servlets.mailService.MailingEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    MailTemplates mailTemplates;
    @Autowired
    PhotoService photoService;
    @Autowired
    VideoService videoService;

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

    @GetMapping("/emailmenu")
    public String getEmailMenu(Model model) {
        model.addAttribute("templates", mailTemplates.getAllExistMailTemplates());
        model.addAttribute("mailingEvents", MailingEventType.values());
        return "emailMenu";
    }

    @GetMapping("/picturemenu")
    public String getPictureMenu(Model model, HttpServletRequest req) {
        File file = new File(appService.getSAVE_PATH() + File.separator + "pictures");
        String rootUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        String[] filesList = file.list();
        for (int i = 0; i < filesList.length; i++) {
            filesList[i] = rootUrl + "/store/pictures/".concat(filesList[i]);
        }
        model.addAttribute("filesList", filesList);
        return "pictureMenu";
    }

    @GetMapping("/photomenu")
    public String getPhotoMenu(Model model, HttpServletRequest req) {
        String rootUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        String photoStore = rootUrl + "/store/photos/";
        Map<String, List<Photo>> photosMap = photoService.getPhotoMap();
        List<String> filesList = new ArrayList<>();
        for (String list : photosMap.keySet()) {
            filesList.addAll(photosMap.get(list).stream().map(photo -> photoStore + photo.getFilename()).collect(Collectors.toList()));
        }
        model.addAttribute("photoFolders", photosMap.keySet());
        model.addAttribute("videoList", videoService.getVideoList());
        model.addAttribute("filesList", filesList);
        return "photoMenu";
    }
}
