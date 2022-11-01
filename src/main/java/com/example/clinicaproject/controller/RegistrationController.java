package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.enums.Role;
import com.example.clinicaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public RegistrationController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public ModelAndView mainPage(ModelAndView model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user != null) {
                long userId = user.getId();
                httpSession.setAttribute("userId", userId);
                model.addObject("userId", userId);
            }
        }
        model.setViewName("main");
        return model;
    }

    @GetMapping("/registration")
    public ModelAndView registrationV(ModelAndView model) {
        model.setViewName("registration");
        return model;
    }

    @PostMapping("/registration")
    public ModelAndView addUserV(@ModelAttribute User user, ModelAndView modelAndView) {
        User userFromDb = userService.findByName(user.getUsername());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        userService.save(user);
        if (user.getRoleSet().contains(Role.VOLUNTEER)) {
            modelAndView.setViewName("redirect:/registerVolunteer");
        }
        if (user.getRoleSet().contains(Role.DOCTOR)) {
            modelAndView.setViewName("redirect:/registerDoctor");
        }
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", new User());
    }
}
