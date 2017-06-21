package lv.challenge.servlets.mvc.controllers;

import lv.challenge.domain.competitors.Contact;
import lv.challenge.domain.competitors.Team;
import lv.challenge.domain.users.User;
import lv.challenge.domain.users.UserRole;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Daniil on 15.05.2017.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private CompetitorService<Team> teamService;
    @Autowired
    CompetitorService<User> userService;
    @Autowired
    CompetitorService<Contact> contactService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(method = RequestMethod.GET)
    public String registerTeam(HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("registrationInfo", new RegistrationInfo());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("registrationInfo") RegistrationInfo info,
                               HttpServletRequest req, HttpServletResponse resp,
                               Model model) {

        Team team = Team.TeamBuilder.createTeam()
                .withName(info.teamName)
                .build();
        Map<String, String> errorsMap =  new HashMap<>();

        Contact contact = Contact.ContactBuilder.createContact()
                .withEmail(info.email)
                .withPhoneNumber(info.phone)
                .build();
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.TEAM_OWNER);
        User user = User.UserBuilder.createUser()
                .withName(info.name)
                .withSurname(info.surname)
                .withOrganisation(info.orgName)
                .withCity(info.city)
                .withState(info.state)
                .withRoles(userRoles)
                .withEnabled(true)
                .withLogin(info.login)
                .withPassword(encoder.encode(info.password1))
                .build();
        errorsMap.putAll(teamService.validate(team, Validator.Purpose.CREATE ));
        errorsMap.putAll(contactService.validate(contact, Validator.Purpose.CREATE));
        errorsMap.putAll(userService.validate(user, Validator.Purpose.CREATE));
        if(!info.password1.equals(info.password2)){
            errorsMap.put("userPasswordErrorMsg","Passwords mismatch");
        }
        if(!errorsMap.isEmpty()){
            model.addAttribute("errors", errorsMap);
            return "registration";
        }
        teamService.saveWithoutValidation(team);
        contactService.saveWithoutValidation(contact);
        user.setContact(contact);
        user.setTeam(team);
        userService.saveWithoutValidation(user);
        return "redirect:menu";
    }

    private static class RegistrationInfo {
        String teamName;
        String orgName;
        String city;
        String state;
        String name;
        String surname;
        String phone;
        String email;
        String login;
        String password1;
        String password2;

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword1() {
            return password1;
        }

        public void setPassword1(String password1) {
            this.password1 = password1;
        }

        public String getPassword2() {
            return password2;
        }

        public void setPassword2(String password2) {
            this.password2 = password2;
        }
    }
}
