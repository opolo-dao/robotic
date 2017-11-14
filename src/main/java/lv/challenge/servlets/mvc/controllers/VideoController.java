package lv.challenge.servlets.mvc.controllers;

import lv.challenge.services.photo_video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping
    public String getVideoPage(Model model) {
        model.addAttribute("videoList", videoService.getVideoList());
        return "video";
    }
}
