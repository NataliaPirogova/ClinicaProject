package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Doctor;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.service.DoctorService;
import com.example.clinicaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("hasAuthority('DOCTOR')")
@Controller
public class DoctorController {

    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public DoctorController(DoctorService doctorService, UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @GetMapping("/registerDoctor")
    public ModelAndView registrationDoctor(ModelAndView modelAndView) {
        modelAndView.setViewName("registrationDoctor");
        return modelAndView;
    }

    @PostMapping("/registerDoctor")
    public ModelAndView registrationDoctorDone(ModelAndView modelAndView, @ModelAttribute Doctor doctor) {
        modelAndView.addObject("doctor", doctor);
        doctor.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        doctor.setUserD(user);
        doctorService.addDoctor(doctor);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("doctor", new Doctor());
    }
}
