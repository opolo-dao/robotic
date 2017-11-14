package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.photo_video.Photo;
import lv.challenge.services.photo_video.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @GetMapping
    public String getPhotoPage(HttpServletRequest req, Model model) {
        Map<String, List<Photo>> photosMap = photoService.getPhotoMap();
        String rootUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        String photoStore = rootUrl + "/store/photos/";
        model.addAttribute("photoStore", photoStore);
        model.addAttribute("photosMap", photosMap);
        return "photo";
    }

    @GetMapping("/getgallery")
    @ResponseBody
    protected List<Photo> getGallery(@RequestParam String galleryId) {
        return photoService.getGalleryById(galleryId);
    }
}
