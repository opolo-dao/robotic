package lv.challenge.servlets.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Daniil on 15.06.2017.
 */
@Controller
@RequestMapping("/errors")
public class ErrorsController {
    @GetMapping
    public String errorRender(Model model,
                              HttpServletRequest req){
        String errorMessage = "";
        int errorCode = getErrorCode(req);
        switch (errorCode){
            case 404:
                errorMessage = "URL not found";
                break;
                default:
                    errorMessage = "Unknown error occurs";
        }
        model.addAttribute("message",errorMessage);
        model.addAttribute("errorCode", errorCode);
        return "errorPage";
    }
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
