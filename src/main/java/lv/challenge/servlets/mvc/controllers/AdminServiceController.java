package lv.challenge.servlets.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Daniil on 11.06.2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminServiceController {
@GetMapping
    public String getAdminPage(Model model){
return "adminMenu";
}
}
