package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.MedicineManufacturer;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.service.MedicineManufacturerService;
import com.example.clinicaproject.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@PreAuthorize("hasAuthority('DOCTOR')")
@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService medicineService;
    private final MedicineManufacturerService medicineManufacturerService;
    private final HttpSession httpSession;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineManufacturerService medicineManufacturerService, HttpSession httpSession) {
        this.medicineService = medicineService;
        this.medicineManufacturerService = medicineManufacturerService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ModelAndView allMedicinePage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Medicine> medicines = medicineService.allMedicines();
        modelAndView.addObject("medicines", medicines);
        modelAndView.setViewName("medicineAll");
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView registrationMedicinePage(ModelAndView modelAndView) {
        List<MedicineManufacturer> manufacturers = medicineManufacturerService.allMedicineManufacturers();
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("registrationMedicine");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView registrationMedicine(@RequestParam(value = "nameManufacturer") String nameManufacturer,
                                             @ModelAttribute("medicine") Medicine medicine, ModelAndView modelAndView) {
        MedicineManufacturer manufacturer = medicineManufacturerService.findByName(nameManufacturer);
        medicine.setManufacturer(manufacturer);
        medicineService.add(medicine);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping(value = "/registerManufacturer")
    public ModelAndView registrationMedicineManufacturer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerManufacturer");
        return modelAndView;
    }

    @PostMapping(value = "/registerManufacturer")
    public ModelAndView registrationMedicineManufacturer(@ModelAttribute("manufacturer") MedicineManufacturer manufacturer) {
        ModelAndView modelAndView = new ModelAndView();
        MedicineManufacturer newManufacturer = medicineManufacturerService.add(manufacturer);
        httpSession.setAttribute("newManufacturer", newManufacturer);
        modelAndView.setViewName("redirect:/medicine/add");
        return modelAndView;
    }

    @GetMapping(value = "test/{medicineId}")
    public ModelAndView infoTestMedicine(ModelAndView modelAndView,
                                         @PathVariable(value = "medicineId") int medicineId) {
        Medicine medicineById = medicineService.getMedicineById(medicineId);
        List<Volunteer> volunteersMedicineById = medicineById.getVolunteer();
        Set<SideEffect> sideEffects = (Set<SideEffect>) medicineById.getSideEffect();
        for (SideEffect s:
                sideEffects) {
        }

        modelAndView.addObject("sideEffects", sideEffects);
        modelAndView.addObject("medicineById", medicineById);
        modelAndView.addObject("volunteersMedicineById", volunteersMedicineById);
        httpSession.setAttribute("medicineId", medicineId);
        modelAndView.setViewName("medicine");
        return modelAndView;
    }

    @PostMapping(value = "test/{medicineId}")
    public ModelAndView editTestMedicine(ModelAndView modelAndView,
                                         @PathVariable(value = "medicineId") int medicineId) {
        Medicine medicineById = medicineService.getMedicineById(medicineId);
        List<Volunteer> volunteersForMedicineResearch =
                (List<Volunteer>) httpSession.getAttribute("volunteersForMedicineResearch");
        medicineById.setVolunteer(volunteersForMedicineResearch);
        modelAndView.setViewName("medicine");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("manufacturer", new MedicineManufacturer());
    }
}
