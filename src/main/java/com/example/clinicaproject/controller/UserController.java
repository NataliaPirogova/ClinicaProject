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
import java.util.Set;

@Controller
public class UserController {

    private final VolunteerService volunteerService;
    private final SideEffectService sideEffectService;
    private final UserService userService;

    @Autowired
    public UserController(VolunteerService volunteerService,
                          SideEffectService sideEffectService,
                          UserService userService) {
        this.volunteerService = volunteerService;
        this.sideEffectService = sideEffectService;
        this.userService = userService;
    }

    @GetMapping("/volunteer/{userId}")
    public ModelAndView editUserV(ModelAndView modelAndView, @PathVariable long userId) {
        Volunteer volunteerID = volunteerService.findByUserV(userService.findById(userId));
        Medicine medicineVolunteerID = volunteerID.getMedicine();
        Set<SideEffect> sideEffectList = sideEffectService.allSideEffects();
        Set<SideEffect> sideEffectListV = volunteerID.getSideEffectList();
        modelAndView.addObject("sideEffectList", sideEffectList);
        modelAndView.addObject("sideEffectListV", sideEffectListV);
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
        long userId = user.getId();
        modelAndView.addObject("userId", userId);
        int volunteerId = volunteer.getId();
        modelAndView.addObject("volunteerId", volunteerId);
        modelAndView.setViewName("redirect:/volunteer/{userId}");
        return modelAndView;
    }

    @PostMapping("/editSideEffects")
    public ModelAndView editUserVDoneE(ModelAndView modelAndView,
                                       @ModelAttribute SideEffect sideEffect) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Volunteer volunteer = volunteerService.findByUserV(user);
        SideEffect sideEffectChoosen = sideEffectService.findByName(sideEffect.getName());
        List<Volunteer> volunteersForThisSideEffect = sideEffectChoosen.getVolunteerList();
        List<Medicine> medicinesForThisSideEffect = sideEffectChoosen.getMedicine();
        Medicine medicineVolunteerID = volunteer.getMedicine();
        volunteersForThisSideEffect.add(volunteer);
        sideEffectChoosen.setVolunteerList(volunteersForThisSideEffect);
        medicinesForThisSideEffect.add(medicineVolunteerID);
        sideEffectChoosen.setMedicine(medicinesForThisSideEffect);
        sideEffectService.editSideEffect(sideEffectChoosen);
        long userId = user.getId();
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("redirect:/volunteer/{userId}");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("sideEffect", new SideEffect());
    }
}
