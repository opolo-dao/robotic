package lv.challenge.servlets.mvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 19.06.2017.
 */
@RestController
@RequestMapping("/rest")
public class RestFullController {
    @GetMapping("/hello")
    @ResponseBody
    private Map<String, List<List<String>>> hello() {
        Map<String, List<List<String>>> map = new HashMap<>();
/*        List<String> list = new ArrayList<>();
        List<String > list2 = new ArrayList<>();
        list2.add("Vasily");
        list2.add("Pupkind");
        list.add("Daniil");
        list.add("Opoclhenov,Zubrik");
        List<List<String>> listArr = new ArrayList<>();
        listArr.add(list);
        listArr.add(list2);
        map.put("data", listArr);*/
        return map;
    }
}

