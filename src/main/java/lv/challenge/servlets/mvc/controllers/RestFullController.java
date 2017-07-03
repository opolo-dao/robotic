package lv.challenge.servlets.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ViewResolver;

/**
 * Created by Daniil on 19.06.2017.
 */
@RestController
@RequestMapping("/rest")
public class RestFullController {
    @Autowired
    ViewResolver viewResolver;
    @GetMapping("/hello")
    @ResponseBody
    @CrossOrigin
    private String hello() throws Exception {
        return "robotsToCheck";
    }
}

