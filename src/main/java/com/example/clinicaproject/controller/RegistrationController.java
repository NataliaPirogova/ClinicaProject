package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Role;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registrationV")
    public ModelAndView registration(ModelAndView model) {
        model.setViewName("registration");
        return model;
    }

    @PostMapping("/registrationV")
    public ModelAndView addUser(@ModelAttribute User user, ModelAndView modelAndView) {
        User userFromDb = userService.findByName(user.getUsername());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        user.setRoleSet(new HashSet<>(List.of(Role.VOLUNTEER)));
        userService.save(user);
//        httpSession.setAttribute("user1", user);
        modelAndView.setViewName("redirect:/registerVolunteer");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", new User());
    }
}
