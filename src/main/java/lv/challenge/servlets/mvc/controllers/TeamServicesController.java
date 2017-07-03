package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.competitors.Contact;
import lv.challenge.domain.competitors.Participant;
import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.competitors.Team;
import lv.challenge.domain.users.User;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import lv.challenge.servlets.mvc.StateData;
import lv.challenge.servlets.security.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Daniil on 22.05.2017.
 */
@Controller
@RequestMapping("/menu")

public class TeamServicesController {
    @Autowired
    CompetitorService<Participant> participantService;
    @Autowired
    CompetitorService<Contact> contactService;
    @Autowired
    CompetitorService<Robot> robotService;
    @Autowired
    CompetitorService<Team> teamService;
    @Autowired
    CompetitorService<User> userService;
    @Autowired
    StateData stateData;

    @GetMapping
    public String getTeamMenu(Authentication auth,
                              HttpServletRequest req,
                              HttpSession session,
                              Model model) {
        User user = ((MyUserDetail) auth.getPrincipal()).getUser();
        Team team = teamService.getByIdWithCollections(user.getTeam().getId()).get();
        Set<Robot> robotToDelete = team.getRobots()
                .stream()
                .filter(robot -> robot.getOperators().isEmpty())
                .collect(Collectors.toSet());
        for (Robot robot : robotToDelete) {
            robotService.deleteById(robot.getId());
            team.getRobots().remove(robot);
        }
        if (!robotToDelete.isEmpty()) {
            teamService.update(team);
            model.addAttribute("deletedRobots", robotToDelete);
        }
        model.addAttribute("team", team);
        model.addAttribute("user", user);
        session.setAttribute("teamid", team.getId());
        return "teamMenu";
    }

    @GetMapping("/edituser")
    public String editUserForm(Authentication auth,
                               Model model) {
        User user = ((MyUserDetail) auth.getPrincipal()).getUser();
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edituser")
    public String editUser(@RequestParam("teamName") String teamName,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("orgName") String orgName,
                           @RequestParam("city") String city,
                           @RequestParam("state") String state,
                           @RequestParam("phone") String phone,
                           @RequestParam("email") String email,
                           Model model,
                           Authentication auth) {
        User user = ((MyUserDetail) auth.getPrincipal()).getUser();
        Map<String, String> errorsMap = new HashMap<>();
        user.getContact().setPhoneNumber(phone);
        user.getContact().setEmail(email);
        user.getTeam().setName(teamName);
        errorsMap.putAll(contactService.validate(user.getContact(), Validator.Purpose.UPDATE));
        errorsMap.putAll(teamService.validate(user.getTeam(), Validator.Purpose.UPDATE));
        if (!errorsMap.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("errors", errorsMap);
            return "editUser";
        }
        user.setName(name);
        user.setSurname(surname);
        user.setOrganisation(orgName);
        user.setState(state);
        user.setCity(city);
        teamService.update(user.getTeam());
        contactService.update(user.getContact());
        userService.update(user);
        return "redirect:/menu#info";
    }

    @GetMapping("/addmember")
    public String addMemberForm(Model model) {
        model.addAttribute("formToken", stateData.getToken());
        return "addMember";
    }

    @PostMapping("/addmember")
    public String addMember(@SessionAttribute("teamid") int teamid,
                            @RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("age") int age,
                            @RequestParam("email") String email,
                            @RequestParam("phone") String phone,
                            HttpSession session,
                            Model model) {

        Map<String, String> errorsMap = new HashMap<>();
        Contact contact = Contact.ContactBuilder.createContact()
                .withEmail(email)
                .withPhoneNumber(phone)
                .build();
        Participant member = Participant.ParticipantBuilder.createParticipant()
                .withName(name)
                .withSurname(surname)
                .withAge(age)
                .withContact(contact)
                .withTeam(teamService.loadById(teamid).get())
                .build();
        errorsMap.putAll(participantService.validate(member, Validator.Purpose.CREATE));
        if (!errorsMap.isEmpty()) {
            model.addAttribute("errors", errorsMap);
            model.addAttribute("member", member);
            return "addMember";
        }
        participantService.saveWithoutValidation(member);
        return "redirect:/menu#members";
    }

    @GetMapping("/editmember")
    public String editMemberForm(@RequestParam("id") int id,
                                 HttpSession session) {
        Participant member = participantService.getByIdWithCollections(id).get();
        session.setAttribute("member", member);
        return "editMember";
    }

    @PostMapping("/editmember")
    public String updateMember(@SessionAttribute int teamid,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("age") int age,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               HttpSession session,
                               Model model) {
        Participant member = (Participant) session.getAttribute("member");
        member.setName(name);
        member.setSurname(surname);
        member.setAge(age);
        if (member.getContact() == null) {
            member.setContact(new Contact());
        }
        member.getContact().setEmail(email);
        member.getContact().setPhoneNumber(phone);
        Map<String, String> errorsMap = participantService.update(member);
        if (errorsMap.isEmpty()) {
            session.removeAttribute("member");
            return "redirect:/menu#members";
        }
        model.addAttribute("errorsMap", errorsMap);
        return "editMember";
    }

    @GetMapping("/removemember")
    public String removeMembrer(@SessionAttribute("teamid") int teamid,
                                @RequestParam("id") int id) {
        participantService.deleteById(id);
        return "redirect:/menu#members";
    }

    @GetMapping("/addrobot")
    public String addRobotForm(@SessionAttribute("teamid") int teamid,
                               Model model) {
        model.addAttribute("competitions", Arrays.asList(CompetitionType.values()));
        model.addAttribute("members", teamService.getByIdWithCollections(teamid).get().getParticipants());
        return "addRobot";
    }

    @PostMapping("/addrobot")
    public String addRobot(@SessionAttribute("teamid") int teamid,
                           @RequestParam("name") String name,
                           @RequestParam(value = "competitions", required = false) Set<CompetitionType> competitions,
                           @RequestParam(value = "operatorsId", required = false) List<Integer> operatorsId,
                           Model model,
                           HttpServletRequest req) {
        Robot robot = Robot.RobotBuilder.createRobot()
                .withName(name)
                .withTeam(teamService.loadById(teamid).get())
                .build();
        Set<Participant> operators = null;
        if (operatorsId != null) operators = participantService.getByMultipleIds(operatorsId);
        robot.setOperators(operators);
        robot.setCompetitions(competitions);
        Map<String, String> errorsMap = robotService.register(robot);
        if (!errorsMap.isEmpty()) {
            model.addAttribute("errors", errorsMap);
            model.addAttribute("competitions", Arrays.asList(CompetitionType.values()));
            model.addAttribute("members", participantService.getAllByTeamId(teamid));
            model.addAttribute("name", name);
            model.addAttribute("checkedCompetitions", competitions);
            model.addAttribute("checkedParticipants", operatorsId);
            return "addRobot";
        }
        return "redirect:/menu#robots";
    }

    @GetMapping("/editrobot")
    public String editRobotForm(@SessionAttribute("teamid") int teamid,
                                @RequestParam("id") int id,
                                Model model) {
        Optional<Robot> robot = robotService.getByIdWithCollections(id);
        if (robot.isPresent()) {
            model.addAttribute("robot", robot.orElse(new Robot()));
        }
        model.addAttribute("competitions", Arrays.asList(CompetitionType.values()));
        model.addAttribute("members", participantService.getAllByTeamId(teamid));
        return "editRobot";
    }

    @PostMapping("/editrobot")
    public String editRobot(@SessionAttribute("teamid") int teamid,
                            @RequestParam("name") String name,
                            @RequestParam(value = "competitions", required = false) Set<CompetitionType> competitions,
                            @RequestParam(value = "operatorsId", required = false) List<Integer> operatorsId,
                            @RequestParam("id") int id,
                            Model model) {
        Optional<Robot> robot = robotService.getById(id);
        String oldName = robot.get().getName();
        Map<String, String> errorsMap;
        Set<Participant> operators = null;
        if (operatorsId != null) operators = participantService.getByMultipleIds(operatorsId);
        if (robot.isPresent()) {
            robot.get().setCompetitions(competitions);
            robot.get().setOperators(operators);
            robot.get().setName(name);
            robot.get().setChecked(false);
            errorsMap = robotService.update(robot.get());
            if (!errorsMap.isEmpty()) {
                model.addAttribute("oldName", oldName);
                model.addAttribute("errors", errorsMap);
                model.addAttribute("competitions", Arrays.asList(CompetitionType.values()));
                model.addAttribute("members", participantService.getAllByTeamId(teamid));
                model.addAttribute("robot", robot.get());
                model.addAttribute("checkedCompetitions", competitions);
                model.addAttribute("checkedParticipants", operatorsId);
                return "editRobot";
            }

        }
        return "redirect:/menu#robots";
    }

    @GetMapping("/removerobot")
    public String removeRobot(@SessionAttribute("teamid") int teamid,
                              @RequestParam("id") int id) {
        robotService.deleteById(id);
        return "redirect:/menu#robots";
    }
}
