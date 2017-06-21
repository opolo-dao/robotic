package lv.challenge.servlets.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Daniil on 06-May-17.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    BCryptPasswordEncoder encoder;
    @GetMapping
    public String getWelcome(HttpServletRequest req){
        return "home";
    }
}

