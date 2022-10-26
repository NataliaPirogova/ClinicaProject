package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.model.VolunteerPrimaryHealthInfo;
import com.example.clinicaproject.model.enums.*;
import com.example.clinicaproject.service.MedicineService;
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
    private final MedicineService medicineService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService,
                               VolunteerHabitsInfoService volunteerHabitsInfoService,
                               VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService, HttpSession httpSession, MedicineService medicineService) {
        this.volunteerService = volunteerService;
        this.volunteerHabitsInfoService = volunteerHabitsInfoService;
        this.volunteerPrimaryHealthInfoService = volunteerPrimaryHealthInfoService;
        this.httpSession = httpSession;
        this.medicineService = medicineService;
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
    public ModelAndView volunteersAll1(
//            @RequestParam(value = "DoB", required = false) String DoB,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "firstName", required = false) String firstName,
                                       @RequestParam(value = "middleName", required = false) String middleName,
                                       @RequestParam(value = "lastName", required = false) String lastName,
                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       ModelAndView modelAndView, RedirectAttributes ra) {
        ra
//                .addAttribute("DoB", DoB)
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
    public ModelAndView volunteersAll2(
//            @RequestParam(value = "DoB", required = false) String DoB,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "firstName", required = false) String firstName,
                                       @RequestParam(value = "middleName", required = false) String middleName,
                                       @RequestParam(value = "lastName", required = false) String lastName,
                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       ModelAndView modelAndView) {
        List<Volunteer> volunteers = volunteerService.allVolunteers();
//        modelAndView.addObject("volunteers", volunteers);
//        if (DoB != null) {
//            volunteers = volunteers.stream()
//                    .filter(v -> v.getDoB().equals(LocalDate.parse(DoB))).collect(Collectors.toList());
//        }
        if (!email.isBlank()) {
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
        httpSession.setAttribute("volunteersForMedicineResearch", volunteers);
        modelAndView.setViewName("volunteersAllF");
        return modelAndView;
    }

    @PostMapping("/volunteersAllFiltered")
    public ModelAndView volunteersAll2(ModelAndView modelAndView) {
        List<Volunteer> volunteers = (List<Volunteer>) httpSession.getAttribute("volunteersForMedicineResearch");
        if (httpSession.getAttribute("medicineId") != null) {
            int medicineId = (int) httpSession.getAttribute("medicineId");
            for (Volunteer volunteer :
                    volunteers) {
                volunteer.setMedicine(medicineService.getMedicineById(medicineId));
                volunteerService.editVolunteer(volunteer);
            }
        }
        modelAndView.setViewName("redirect:/");
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
        List<VolunteerHabitsInfo> volunteersAllByHabitsInfo = volunteerHabitsInfoService.allVolunteerHabitsInfo();
        modelAndView.addObject("volunteersAllByHabitsInfo", volunteersAllByHabitsInfo);
        modelAndView.setViewName("volunteersAllByHabitsInfo");
        int medicineId = (int) httpSession.getAttribute("medicineId");
        return modelAndView;
    }

    @PostMapping("/volunteersAllByHabitsInfo")
    public ModelAndView volunteersAllByHabitsInfoFiltered(
            @RequestParam(value = "smoking", required = false) String smoking,
            @RequestParam(value = "takingDrugs", required = false) String takingDrugs,
            @RequestParam(value = "takingMedicines", required = false) String takingMedicines,
            @RequestParam(value = "pregnantNow", required = false) String pregnantNow,
            @RequestParam(value = "isPlanningPregnancy", required = false) String isPlanningPregnancy,
            @RequestParam(value = "vegetarian", required = false) String vegetarian,
            @RequestParam(value = "takingHormonalContraceptives", required = false) String takingHormonalContraceptives,
            @RequestParam(value = "sport", required = false) String sport,
            @RequestParam(value = "alcohol", required = false) String alcohol,
            ModelAndView modelAndView,
            RedirectAttributes ra) {
        ra.addAttribute("smoking", smoking)
                .addAttribute("takingDrugs", takingDrugs)
                .addAttribute("takingMedicines", takingMedicines)
                .addAttribute("pregnantNow", pregnantNow)
                .addAttribute("isPlanningPregnancy", isPlanningPregnancy)
                .addAttribute("vegetarian", vegetarian)
                .addAttribute("takingHormonalContraceptives", takingHormonalContraceptives)
                .addAttribute("sport", sport)
                .addAttribute("alcohol", alcohol);
        modelAndView.setViewName("redirect:/volunteersAllByHabitsInfoFiltered");
        return modelAndView;
    }

    @GetMapping("/volunteersAllByHabitsInfoFiltered")
    public ModelAndView volunteersAllByHabitsInfo2(@RequestParam(value = "smoking", required = false) String smoking,
                                                   @RequestParam(value = "takingDrugs", required = false) String takingDrugs,
                                                   @RequestParam(value = "takingMedicines", required = false) String takingMedicines,
                                                   @RequestParam(value = "pregnantNow", required = false) String pregnantNow,
                                                   @RequestParam(value = "isPlanningPregnancy", required = false) String isPlanningPregnancy,
                                                   @RequestParam(value = "vegetarian", required = false) String vegetarian,
                                                   @RequestParam(value = "takingHormonalContraceptives", required = false) String takingHormonalContraceptives,
                                                   @RequestParam(value = "sport", required = false) String sport,
                                                   @RequestParam(value = "alcohol", required = false) String alcohol,
                                                   ModelAndView modelAndView) {
        List<VolunteerHabitsInfo> volunteersAllByHabitsInfo = volunteerHabitsInfoService.allVolunteerHabitsInfo();
        modelAndView.addObject("volunteersAllByHabitsInfo", volunteersAllByHabitsInfo);
        switch (Smoking.valueOf(smoking)) {
            case NO -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSmoking().equals(Smoking.NO)).collect(Collectors.toList());
            case SMOKE_EARLIER -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSmoking().equals(Smoking.SMOKE_EARLIER)).collect(Collectors.toList());
            case SMOKE_SEVERAL_TIMES_A_MONTH -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSmoking().equals(Smoking.SMOKE_SEVERAL_TIMES_A_MONTH)).collect(Collectors.toList());
            case SMOKE_1_TIME_A_WEEK_OR_MORE_OFTEN -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSmoking().equals(Smoking.SMOKE_1_TIME_A_WEEK_OR_MORE_OFTEN)).collect(Collectors.toList());
        }
        switch (TakingDrugs.valueOf(takingDrugs)) {
            case NO -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getTakingDrugs().equals(TakingDrugs.NO)).collect(Collectors.toList());
            case NOT_NOW_BUT_EARLIER -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getTakingDrugs().equals(TakingDrugs.NOT_NOW_BUT_EARLIER)).collect(Collectors.toList());
            case YES -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getTakingDrugs().equals(TakingDrugs.YES)).collect(Collectors.toList());
        }
        if (takingMedicines.equals("true")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(VolunteerHabitsInfo::isTakingMedicines).collect(Collectors.toList());
        }
        if (takingMedicines.equals("false")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> (!v.isTakingMedicines())).collect(Collectors.toList());
        }
        if (pregnantNow.equals("true")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(VolunteerHabitsInfo::isPregnantNow).collect(Collectors.toList());
        }
        if (pregnantNow.equals("false")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> (!v.isPregnantNow())).collect(Collectors.toList());
        }
        switch (PlanningPregnancy.valueOf(isPlanningPregnancy)) {
            case PLANNING_THIS_YEAR -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getIsPlanningPregnancy().equals(PlanningPregnancy.PLANNING_THIS_YEAR)).collect(Collectors.toList());
            case PLANNING_A_YEAR_AFTER_OR_LATER -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getIsPlanningPregnancy().equals(PlanningPregnancy.PLANNING_A_YEAR_AFTER_OR_LATER)).collect(Collectors.toList());
            case NOT_PLANNING -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getIsPlanningPregnancy().equals(PlanningPregnancy.NOT_PLANNING)).collect(Collectors.toList());
        }
        if (vegetarian.equals("true")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(VolunteerHabitsInfo::isVegetarian).collect(Collectors.toList());
        }
        if (vegetarian.equals("false")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> (!v.isVegetarian())).collect(Collectors.toList());
        }
        if (takingHormonalContraceptives.equals("true")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(VolunteerHabitsInfo::isTakingHormonalContraceptives).collect(Collectors.toList());
        }
        if (takingHormonalContraceptives.equals("false")) {
            volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> (!v.isTakingHormonalContraceptives())).collect(Collectors.toList());
        }
        switch (Sport.valueOf(sport)) {
            case NO -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSport().equals(Sport.NO)).collect(Collectors.toList());
            case PROFESSIONAL -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSport().equals(Sport.PROFESSIONAL)).collect(Collectors.toList());
            case AMATEUR_SPORTS -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getSport().equals(Sport.AMATEUR_SPORTS)).collect(Collectors.toList());
        }
        switch (Alcohol.valueOf(alcohol)) {
            case NO -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getAlcohol().equals(Alcohol.NO)).collect(Collectors.toList());
            case EVERY_DAY -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getAlcohol().equals(Alcohol.EVERY_DAY)).collect(Collectors.toList());
            case ONLY_ON_HOLIDAYS -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getAlcohol().equals(Alcohol.ONLY_ON_HOLIDAYS)).collect(Collectors.toList());
            case ONE_OR_TWO_TIMES_A_WEEK -> volunteersAllByHabitsInfo = volunteersAllByHabitsInfo.stream()
                    .filter(v -> v.getAlcohol().equals(Alcohol.ONE_OR_TWO_TIMES_A_WEEK)).collect(Collectors.toList());
        }
        modelAndView.addObject("volunteersAllByHabitsInfo", volunteersAllByHabitsInfo);
        modelAndView.setViewName("volunteersAllByHabitsInfo");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("volunteerHabitsInfo", new VolunteerHabitsInfo());
        model.addAttribute("volunteerPrimaryHealthInfo", new VolunteerPrimaryHealthInfo());
    }
}