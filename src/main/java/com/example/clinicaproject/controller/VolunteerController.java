package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.model.VolunteerPrimaryHealthInfo;
import com.example.clinicaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@PreAuthorize("hasAuthority('VOLUNTEER')")
@Controller
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final VolunteerHabitsInfoService volunteerHabitsInfoService;
    private final VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService;
    private final HttpSession httpSession;
    private final MedicineService medicineService;
    private final UserService userService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService,
                               VolunteerHabitsInfoService volunteerHabitsInfoService,
                               VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService,
                               HttpSession httpSession,
                               MedicineService medicineService,
                               UserService userService) {
        this.volunteerService = volunteerService;
        this.volunteerHabitsInfoService = volunteerHabitsInfoService;
        this.volunteerPrimaryHealthInfoService = volunteerPrimaryHealthInfoService;
        this.httpSession = httpSession;
        this.medicineService = medicineService;
        this.userService = userService;
    }

    @GetMapping(value = "/registerVolunteer")
    public ModelAndView registerPageVolunteer(ModelAndView modelAndView) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        long userId = user.getId();
        httpSession.setAttribute("userId", userId);
        modelAndView.setViewName("registrationVolunteer");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteer")
    public ModelAndView registerVolunteer(ModelAndView modelAndView,
                                          @ModelAttribute Volunteer volunteer) {
        volunteer.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        volunteer.setUserV(user);
        Volunteer volunteer1 = volunteerService.addVolunteer(volunteer);
        httpSession.setAttribute("newVolunteer", volunteer1);
        modelAndView.setViewName("redirect:/registrationVolunteerHabitsInfo");
        return modelAndView;
    }

    @GetMapping(value = "/registrationVolunteerHabitsInfo")
    public ModelAndView registerPageVolunteerHabits(ModelAndView modelAndView) {
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("newVolunteer");
        modelAndView.addObject("volunteer", volunteer);
        modelAndView.setViewName("registrationVolunteerHabitsInfo");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteerHabitsInfo")
    public ModelAndView registerVolunteerHabits(ModelAndView modelAndView,
                                                @ModelAttribute("volunteerHabitsInfo") VolunteerHabitsInfo volunteerHabitsInfo) {
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("newVolunteer");
        volunteerHabitsInfo.setVolunteer(volunteer);
        volunteerHabitsInfoService.addVolunteerHabitsInfo(volunteerHabitsInfo);
        modelAndView.setViewName("redirect:/registrationVolunteerPrimaryHealthInfo");
        return modelAndView;
    }

    @GetMapping(value = "/registrationVolunteerPrimaryHealthInfo")
    public ModelAndView registerPageVolunteerPrimaryHealthInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("registrationVolunteerPrimaryHealthInfo");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteerPrimaryHealthInfo")
    public ModelAndView registerVolunteerPrimaryHealthInfo(ModelAndView modelAndView,
                                                           @ModelAttribute("volunteerPrimaryHealthInfo") VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo) {
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("newVolunteer");
        long userId = (long) httpSession.getAttribute("userId");
        volunteerPrimaryHealthInfo.setVolunteer(volunteer);
        volunteerPrimaryHealthInfoService.addVolunteerPrimaryHealthInfo(volunteerPrimaryHealthInfo);
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("redirect:/volunteer/{userId}");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("volunteerHabitsInfo", new VolunteerHabitsInfo());
        model.addAttribute("volunteerPrimaryHealthInfo", new VolunteerPrimaryHealthInfo());
    }
}