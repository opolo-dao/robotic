package lv.challenge.servlets.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operator")
public class OperatorsController {
    @GetMapping("/lf")
    public String getLfOperatorPage() {
        return "lfOperator";
    }
}
