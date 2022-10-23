package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.model.VolunteerPrimaryHealthInfo;
import com.example.clinicaproject.model.enums.Gender;
import com.example.clinicaproject.model.enums.Smoking;
import com.example.clinicaproject.service.VolunteerHabitsInfoService;
import com.example.clinicaproject.service.VolunteerPrimaryHealthInfoService;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final VolunteerHabitsInfoService volunteerHabitsInfoService;
    private final VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService;
    private final HttpSession httpSession;

    @Autowired
    public VolunteerController(VolunteerService volunteerService,
                               VolunteerHabitsInfoService volunteerHabitsInfoService,
                               VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService, HttpSession httpSession) {
        this.volunteerService = volunteerService;
        this.volunteerHabitsInfoService = volunteerHabitsInfoService;
        this.volunteerPrimaryHealthInfoService = volunteerPrimaryHealthInfoService;
        this.httpSession = httpSession;
    }


    @GetMapping(value = "/registerVolunteer")
    public ModelAndView registerPageVolunteer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationVolunteer");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteer")
    public ModelAndView registerVolunteer(@ModelAttribute("volunteer") Volunteer volunteer) {
        ModelAndView modelAndView = new ModelAndView();
        Volunteer volunteer1 = volunteerService.addVolunteer(volunteer);
        httpSession.setAttribute("newVolunteer", volunteer1);
        modelAndView.setViewName("redirect:/registrationVolunteerHabitsInfo");
        return modelAndView;
    }

    @GetMapping(value = "/registrationVolunteerHabitsInfo")
    public ModelAndView registerPageVolunteerHabits() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationVolunteerHabitsInfo");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteerHabitsInfo")
    public ModelAndView registerVolunteerHabits(@ModelAttribute("volunteerHabitsInfo") VolunteerHabitsInfo volunteerHabitsInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("newVolunteer");
        volunteerHabitsInfo.setVolunteer(volunteer);
        volunteerHabitsInfoService.addVolunteerHabitsInfo(volunteerHabitsInfo);
        modelAndView.setViewName("redirect:/registrationVolunteerPrimaryHealthInfo");
        return modelAndView;
    }

    @GetMapping(value = "/registrationVolunteerPrimaryHealthInfo")
    public ModelAndView registerPageVolunteerPrimaryHealthInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationVolunteerPrimaryHealthInfo");
        return modelAndView;
    }

    @PostMapping(value = "/registrationVolunteerPrimaryHealthInfo")
    public ModelAndView registerVolunteerPrimaryHealthInfo(@ModelAttribute("volunteerPrimaryHealthInfo") VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("newVolunteer");
        volunteerPrimaryHealthInfo.setVolunteer(volunteer);
        volunteerPrimaryHealthInfoService.addVolunteerPrimaryHealthInfo(volunteerPrimaryHealthInfo);
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping("/volunteersAll")
    public ModelAndView volunteersAll(ModelAndView modelAndView) {
        List<Volunteer> volunteers = volunteerService.allVolunteers();
        modelAndView.addObject("volunteers", volunteers);
        modelAndView.setViewName("volunteersAll");
        return modelAndView;
    }

    @PostMapping("/volunteersAll")
    public ModelAndView volunteersAll1(@RequestParam(value = "DoB", required = false) String DoB,
            @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "firstName", required = false) String firstName,
                                       @RequestParam(value = "middleName", required = false) String middleName,
                                       @RequestParam(value = "lastName", required = false) String lastName,
                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       ModelAndView modelAndView, RedirectAttributes ra) {
        ra.addAttribute("DoB", DoB)
                .addAttribute("email", email)
                .addAttribute("firstName", firstName)
                .addAttribute("middleName", middleName)
                .addAttribute("lastName", lastName)
                .addAttribute("phoneNumber", phoneNumber)
                .addAttribute("gender", gender);
        modelAndView.setViewName("redirect:/volunteersAllFiltered");
        return modelAndView;
    }

    @GetMapping("/volunteersAllFiltered")
    public ModelAndView volunteersAll2(@RequestParam(value = "DoB", required = false) String DoB,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "firstName", required = false) String firstName,
                                       @RequestParam(value = "middleName", required = false) String middleName,
                                       @RequestParam(value = "lastName", required = false) String lastName,
                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       ModelAndView modelAndView) {
        List<Volunteer> volunteers = volunteerService.allVolunteers();
        if (DoB.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getDoB().equals(LocalDate.parse(DoB))).collect(Collectors.toList());
        }
        if (email.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getEmail().equals(email)).collect(Collectors.toList());
        }
        if (firstName.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getFirstName().equals(firstName)).collect(Collectors.toList());
        }
        if (middleName.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getMiddleName().equals(middleName)).collect(Collectors.toList());
        }
        if (lastName.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getLastName().equals(lastName)).collect(Collectors.toList());
        }
        if (phoneNumber.length() != 0) {
            volunteers = volunteers.stream()
                    .filter(v -> (v.getPhoneNumber() == Long.valueOf(phoneNumber))).collect(Collectors.toList());
        }
        if (Gender.valueOf(gender) == Gender.FEMALE) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getGender().equals(Gender.FEMALE)).collect(Collectors.toList());
        } else if (Gender.valueOf(gender) == Gender.MALE) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getGender().equals(Gender.MALE)).collect(Collectors.toList());
        }
        modelAndView.addObject("volunteers", volunteers);
        modelAndView.setViewName("volunteersAll");
        return modelAndView;
    }

    @GetMapping("/volunteersAllByPrimaryHealthInfo")
    public ModelAndView volunteersAllByPrimaryHealthInfo(ModelAndView modelAndView) {
        List<VolunteerPrimaryHealthInfo> volunteerPrimaryHealthInfoList = volunteerPrimaryHealthInfoService.allVolunteerPrimaryHealthInfo();
        modelAndView.addObject("volunteerPrimaryHealthInfoAll", volunteerPrimaryHealthInfoList);
        modelAndView.setViewName("volunteersAllByPrimaryHealthInfo");
        return modelAndView;
    }

    @GetMapping("/volunteersAllByHabitsInfo")
    public ModelAndView volunteersAllByHabitsInfo(ModelAndView modelAndView) {
        List<VolunteerHabitsInfo> volunteerHabitsInfoList = volunteerHabitsInfoService.allVolunteerHabitsInfo();
        modelAndView.addObject("volunteerHabitsInfos", volunteerHabitsInfoList);
        modelAndView.setViewName("volunteersAllByHabitsInfo");
        return modelAndView;
    }

    @PostMapping("/volunteersAllByHabitsInfo")
    public ModelAndView volunteersAllByHabitsInfoFiltered(ModelAndView modelAndView,
                                                          RedirectAttributes ra,
                                                          @ModelAttribute("newVolunteerHabitsInfo") VolunteerHabitsInfo newVolunteerHabitsInfo) {
        List<VolunteerHabitsInfo> volunteerHabitsInfoList = volunteerHabitsInfoService.findAllBySmoking(Smoking.NO);
//        ra.addFlashAttribute();
//        VolunteerHabitsInfo volunteerHabitsInfoFilter = (VolunteerHabitsInfo) httpSession.getAttribute("newVolunteerHabitsInfo");
//        volunteerHabitsInfoFilter.setId(-1);
//        volunteerHabitsInfoService.addVolunteerHabitsInfo(volunteerHabitsInfoFilter);
        modelAndView.setViewName("redirect:/volunteersAllByHabitsInfo");
        return modelAndView;
    }
//
//    @GetMapping("/volunteersAllByHabitsInfoFiltered")
//    public ModelAndView volunteersAllByHabitsInfoFiltered(ModelAndView modelAndView) {
//        VolunteerHabitsInfo volunteerHabitsInfoFilter = volunteerHabitsInfoService.findVolunteerHabitsInfo(-1);
//
//        modelAndView.addObject("volunteerHabitsInfoFiltered", volunteerHabitsInfoFiltered);
//        modelAndView.setViewName("volunteersAllByHabitsInfo");
//        return modelAndView;
//    }


    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("volunteerHabitsInfo", new VolunteerHabitsInfo());
        model.addAttribute("volunteerPrimaryHealthInfo", new VolunteerPrimaryHealthInfo());
        model.addAttribute("newVolunteerHabitsInfo", new VolunteerHabitsInfo());
    }
}