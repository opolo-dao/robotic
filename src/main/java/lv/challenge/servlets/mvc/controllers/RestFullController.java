package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.users.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Daniil on 19.06.2017.
 */
@RestController
@RequestMapping("/rest")
public class RestFullController {
@PostMapping("/hello")
@ResponseBody
    private User hello(@RequestBody User user){
    
    return User.UserBuilder.createUser().withName(user.getName()).withSurname(user.getSurname()).build();
}
}

