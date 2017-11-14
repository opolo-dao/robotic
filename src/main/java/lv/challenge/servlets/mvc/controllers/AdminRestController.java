package lv.challenge.servlets.mvc.controllers;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.photo_video.Video;
import lv.challenge.services.common.HTMLStoreService;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.photo_video.PhotoService;
import lv.challenge.services.photo_video.VideoService;
import lv.challenge.services.robot.RobotService;
import lv.challenge.services.tornament.TournamentService;
import lv.challenge.servlets.mailService.MailTemplates;
import lv.challenge.servlets.mailService.MailingEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 17.07.2017.
 */
@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {
    @Autowired
    TournamentService tournamentService;
    @Autowired
    CompetitorService<Robot> robotService;
    @Autowired
    ApplicationService appService;
    @Autowired
    HTMLStoreService htmlStoreService;
    @Autowired
    MailTemplates mailTemplates;
    @Autowired
    PhotoService photoService;
    @Autowired
    VideoService videoService;

    @PostMapping("/uploadimage")
    protected void uploadImage(@RequestParam MultipartFile file,
                               @RequestParam String name,
                               HttpServletRequest req) {
        String originalFilename = file.getOriginalFilename();
        try (FileOutputStream fos = new FileOutputStream(appService.SAVE_PATH + File.separator + "pictures" + File.separator + name + originalFilename.substring(originalFilename.lastIndexOf(".")))) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("cant upload picture");
        }
    }

    @PostMapping("/uploadphoto")
    protected String uploadImage(@RequestParam MultipartFile file,
                                 @RequestParam String photoName,
                                 @RequestParam String title,
                                 @RequestParam String photoFolder,
                                 @RequestParam String description,
                                 HttpServletRequest req) {

        return photoService.savePhoto(file, photoName, photoFolder, title, description);
    }

    @PostMapping("/deletephoto")
    protected void deletePhoto(@RequestParam String photo) {
        photoService.deletePhoto(photo);
    }

    @PostMapping("/addvideo")
    protected void addVideo(@RequestParam String videoId,
                            @RequestParam String videoTitle) {
        videoService.saveVideoId(videoId, videoTitle);
    }

    @PostMapping("/deletevideo")
    protected void deleteVideo(@RequestParam String video) {
        String[] attr = video.split(",");
        if (attr.length < 2) {
            videoService.deleteVideo(new Video(attr[0], ""));
        } else
            videoService.deleteVideo(new Video(attr[0], attr[1]));

    }

    @PostMapping("/deleteimage")
    protected void deleteImage(@RequestParam String image) {
        String imageName = image.substring(image.lastIndexOf('/') + 1);
        Path filename = Paths.get(appService.SAVE_PATH + File.separator + "pictures" + File.separator + imageName);
        try {
            Files.delete(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getemailtemplate")
    protected String geteMailTemplate(@RequestParam String templateName) {
        return mailTemplates.getMailTemplate(templateName);

    }

    @GetMapping("/robotstochecklist")
    @ResponseBody
    protected Map<String, List<List<String>>> getRobotsToCheckList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getRobotsToAccept());
        return map;
    }

    @PostMapping("/acceptrobot")
    protected void acceptRobot(@RequestParam int id) {

        ((RobotService) robotService).setRobotChecked(id);
    }

    @PostMapping("/commentrobot")
    protected void commentRobot(@RequestParam int id,
                                @RequestParam String comment) {
        ((RobotService) robotService).setRobotComment(id, comment);
    }

    @GetMapping("/gettournamentstatistic")
    @ResponseBody
    protected Map<String, Object> getTournamentStatistic(
            @RequestParam Integer tournamentId) {
        return tournamentService.getTournamentStatistic(tournamentId);
    }

    @GetMapping("/setactivetournament")
    @ResponseStatus(HttpStatus.OK)
    protected void setActiveTournament(HttpServletResponse resp,
                                       @RequestParam Integer tournamentId) throws IOException {
        tournamentService.setActiveTournament(tournamentId);
    }

    @GetMapping("/deletetournament")
    protected void deleteTournament(@RequestParam Integer tournamentId) {
        tournamentService.deleteById(tournamentId);
    }

    @PostMapping("/updaterules")
    protected void updateRule(@RequestParam String part,
                              @RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        htmlStoreService.writeToHTMLFileStore(text, competitionName + File.separator + part, LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateaboutcompetitions")
    protected void updateAboutCompetition(@RequestParam String text,
                                          HttpServletRequest req) {
        String referer = req.getHeader(HttpHeaders.REFERER);
        String competitionName = referer.substring(referer.lastIndexOf("/") + 1);
        htmlStoreService.writeToHTMLFileStore(text, "aboutCompetitions" + File.separator + competitionName, LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateabout")
    protected void updateAbout(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "about", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateagenda")
    protected void updateAgenda(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "agenda", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updatehome")
    protected void updateHome(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "home", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updategeneralrules")
    protected void updateGeneralRules(@RequestParam String text) {

        htmlStoreService.writeToHTMLFileStore(text, "generalrules", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updatefooter")
    protected void updateFooter(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "footer", LocaleContextHolder.getLocale());
    }

    @PostMapping("/updateresults")
    protected void updateRule(@RequestParam String text,
                              HttpServletRequest req) {

        String referer = req.getHeader(HttpHeaders.REFERER);
        String[] paths = referer.split("/");
        String competitionName = paths[paths.length - 1];
        String tournamentId = paths[paths.length - 2];
        htmlStoreService.writeToHTMLFileStore(text, "results" + File.separator + tournamentId + "_" + competitionName, LocaleContextHolder.getLocale());
    }

    @PostMapping("/updatemailtemplate")
    protected String updateMailTemplate(@RequestParam String templateName, @RequestParam String text) {
        return mailTemplates.updateMailTemplate(templateName, text);
    }

    @PostMapping("/deletemailtemplate")
    protected String deleteMailTemplate(@RequestParam String templateName) {
        return mailTemplates.deleteMailTemplate(templateName);
    }

    @PostMapping("/setmailingeventtemplate")
    protected String setMailingEventTemplate(@RequestParam String templateName,
                                             @RequestParam MailingEventType eventType) {
        return mailTemplates.setMailingEventTemplate(templateName, eventType);
    }
}
