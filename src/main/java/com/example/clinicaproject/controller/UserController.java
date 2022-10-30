package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.service.SideEffectService;
import com.example.clinicaproject.service.UserService;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final VolunteerService volunteerService;
    private final SideEffectService sideEffectService;
    private final UserService userService;

    @Autowired
    public UserController(VolunteerService volunteerService, SideEffectService sideEffectService, UserService userService) {
        this.volunteerService = volunteerService;
        this.sideEffectService = sideEffectService;
        this.userService = userService;
    }

    @GetMapping("/volunteer/{id}")
    public ModelAndView editUserV(ModelAndView modelAndView, @PathVariable int id) {
        Volunteer volunteerID = volunteerService.getVolunteerByID(id);
        Medicine medicineVolunteerID = volunteerID.getMedicine();
        if (medicineVolunteerID != null) {
            List<SideEffect> sideEffectSet = volunteerID.getSideEffectSet();
            modelAndView.addObject("sideEffectSet", sideEffectSet);
        }
        modelAndView.addObject("volunteerID", volunteerID);
        modelAndView.addObject("medicineVolunteerID", medicineVolunteerID);
        modelAndView.setViewName("userVInformation");
        return modelAndView;
    }

    @PostMapping("/editVolunteer")
    public ModelAndView editUserVDoneV(ModelAndView modelAndView,
                                       @ModelAttribute("volunteerID") Volunteer volunteer) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        volunteer = volunteerService.findByUserV(user);
        volunteerService.editVolunteer(volunteer);
        int id = volunteer.getId();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("redirect:/volunteer/{id}");
        return modelAndView;
    }

    @PostMapping("/editSideEffects")
    public ModelAndView editUserVDoneE(ModelAndView modelAndView,
                                       @ModelAttribute SideEffect sideEffect) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Volunteer volunteer = volunteerService.findByUserV(user);
        List<Volunteer> volunteersForThisSideEffect = sideEffect.getVolunteerSet();
        volunteersForThisSideEffect.add(volunteer);
        sideEffect.setVolunteerSet(volunteersForThisSideEffect);
        List<Medicine> medicinesForThisSideEffect = sideEffect.getMedicine();
        Medicine medicineVolunteerID = volunteer.getMedicine();
        medicinesForThisSideEffect.add(medicineVolunteerID);
        sideEffect.setMedicine(medicinesForThisSideEffect);
        sideEffectService.editSideEffect(sideEffect);
        int id = volunteer.getId();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("redirect:/volunteer/{id}");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("sideEffect", new SideEffect());
    }
}
