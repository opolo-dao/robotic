package lv.challenge.servlets.mvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniil on 03.07.2017.
 */
@RestController
@RequestMapping("/fieldcontroller")
public class FieldDevicesController {
    @GetMapping
    @ResponseBody
    public Map<String, String> test(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        map.put("Greeting Message", "Hello Field Controller");
        map.put("Your IP", req.getRemoteAddr());
        return map;
    }
}
