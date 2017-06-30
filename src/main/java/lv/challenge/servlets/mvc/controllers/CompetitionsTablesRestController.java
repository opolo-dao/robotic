package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniil on 28.06.2017.
 */
@RestController
@RequestMapping("/rest/participants")
public class CompetitionsTablesRestController {
    @Autowired
    TournamentService tournamentService;

    @GetMapping("/all")
    public Map<String, List<List<String>>> getAllList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getAllCompetitors());
        return map;
    }

    @GetMapping("/legosumo")
    public Map<String, List<List<String>>> getLegoSumoList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.legoSumo));
        return map;
    }

    @GetMapping("/minisumo")
    public Map<String, List<List<String>>> getMiniSumoList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.miniSumo));
        return map;
    }

    @GetMapping("/freelinefollower")
    public Map<String, List<List<String>>> getFreeLinefollowerList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.freeLinefollower));
        return map;
    }

    @GetMapping("/legolinefollower")
    public Map<String, List<List<String>>> getLegoLinefollowerList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.legoLinefollower));
        return map;
    }

    @GetMapping("/legofolkrace")
    public Map<String, List<List<String>>> getLegoFolkraceList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.legoFolkrace));
        return map;
    }

    @GetMapping("/freefolkrace")
    public Map<String, List<List<String>>> getFreeFolkraceList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.freeFolkrace));
        return map;
    }

    @GetMapping("/freelabyrinth")
    public Map<String, List<List<String>>> getFreeLabyrinthList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.freeLabyrinth));
        return map;
    }

    @GetMapping("/legolabyrinth")
    public Map<String, List<List<String>>> getLegoLabyrinthList() {
        Map<String, List<List<String>>> map = new HashMap<>();
        map.put("data", tournamentService.getCompetitionRobots(CompetitionType.legoLabyrinth));
        return map;
    }
}
