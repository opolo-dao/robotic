package lv.challenge.servlets.mvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniil on 19.06.2017.
 */
@RestController
@RequestMapping("/rest")
public class RestFullController {

    @GetMapping("/hello")
    @ResponseBody
    private Map<String, String> hello(HttpServletRequest req) throws Exception {
        Map<String, String> answer = new HashMap<>();
        answer.put("Greeting Message", "Hello rest user");
        answer.put("Your IP", req.getRemoteAddr());
        return answer;
    }
}

