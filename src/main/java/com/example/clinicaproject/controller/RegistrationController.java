package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Role;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/registrationV")
    public ModelAndView registration(ModelAndView model) {
        model.setViewName("registration");
        return model;
    }

    @PostMapping("/registrationV")
    public ModelAndView addUser(@ModelAttribute Volunteer volunteer, ModelAndView modelAndView) {
        Volunteer userFromDb = volunteerService.findByEmail(volunteer.getEmail());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        volunteer.setRoles(new HashSet<>(List.of(Role.VOLUNTEER)));
        volunteerService.addVolunteer(volunteer);
        modelAndView.setViewName("redirect:/registerVolunteer");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("userVolunteer", new Volunteer());
    }
}
