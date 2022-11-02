package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@PreAuthorize("hasAuthority('DOCTOR')")
@Controller
public class VolunteerDBController {

    private final VolunteerService volunteerService;
    private final VolunteerHabitsInfoService volunteerHabitsInfoService;
    private final VolunteerPrimaryHealthInfoService volunteerPrimaryHealthInfoService;
    private final HttpSession httpSession;
    private final MedicineService medicineService;
    private final UserService userService;

    @Autowired
    public VolunteerDBController(VolunteerService volunteerService,
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

    @GetMapping("/volunteersAll")
    public ModelAndView volunteersAll(ModelAndView modelAndView) {
        List<Volunteer> volunteers = volunteerService.allVolunteers();
        modelAndView.addObject("volunteers", volunteers);
        modelAndView.setViewName("volunteersAll");
        return modelAndView;
    }

    @PostMapping("/volunteersAll")
    public ModelAndView volunteersAll1(
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "smoking", required = false) String smoking,
            @RequestParam(value = "takingDrugs", required = false) String takingDrugs,
            @RequestParam(value = "takingMedicines", required = false) String takingMedicines,
            @RequestParam(value = "isPregnantNow", required = false) String isPregnantNow,
            @RequestParam(value = "isPlanningPregnancy", required = false) String isPlanningPregnancy,
            @RequestParam(value = "vegetarian", required = false) String vegetarian,
            @RequestParam(value = "takingHormonalContraceptives", required = false) String takingHormonalContraceptives,
            @RequestParam(value = "sport", required = false) String sport,
            @RequestParam(value = "alcohol", required = false) String alcohol,
            ModelAndView modelAndView, RedirectAttributes ra) {
        ra
                .addAttribute("gender", gender)
                .addAttribute("smoking", smoking)
                .addAttribute("takingDrugs", takingDrugs)
                .addAttribute("takingMedicines", takingMedicines)
                .addAttribute("isPregnantNow", isPregnantNow)
                .addAttribute("isPlanningPregnancy", isPlanningPregnancy)
                .addAttribute("vegetarian", vegetarian)
                .addAttribute("takingHormonalContraceptives", takingHormonalContraceptives)
                .addAttribute("sport", sport)
                .addAttribute("alcohol", alcohol);
        modelAndView.setViewName("redirect:/volunteersAllFiltered");
        return modelAndView;
    }

    @GetMapping("/volunteersAllFiltered")
    public ModelAndView volunteersAll2(
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "smoking", required = false) String smoking,
            @RequestParam(value = "takingDrugs", required = false) String takingDrugs,
            @RequestParam(value = "takingMedicines", required = false) String takingMedicines,
            @RequestParam(value = "isPregnantNow", required = false) String isPregnantNow,
            @RequestParam(value = "isPlanningPregnancy", required = false) String isPlanningPregnancy,
            @RequestParam(value = "vegetarian", required = false) String vegetarian,
            @RequestParam(value = "takingHormonalContraceptives", required = false) String takingHormonalContraceptives,
            @RequestParam(value = "sport", required = false) String sport,
            @RequestParam(value = "alcohol", required = false) String alcohol,
            ModelAndView modelAndView) {
        List<Volunteer> volunteers = volunteerService.findMatchAllByFilters(gender, smoking,
                takingDrugs, takingMedicines, isPregnantNow,
                isPlanningPregnancy, vegetarian, takingHormonalContraceptives,
                sport, alcohol);
        modelAndView.addObject("volunteers", volunteers);
        httpSession.setAttribute("volunteersForMedicineResearch", volunteers);
        modelAndView.setViewName("volunteersAllF");
        return modelAndView;
    }

    @PostMapping("/volunteersAllFiltered")
    public ModelAndView volunteersAll2(ModelAndView modelAndView) {
        List<Volunteer> volunteers =
                (List<Volunteer>) httpSession.getAttribute("volunteersForMedicineResearch");
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

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("volunteer", new Volunteer());
    }
}