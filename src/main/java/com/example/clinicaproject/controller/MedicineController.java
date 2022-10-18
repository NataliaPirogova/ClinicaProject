package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.MedicineManufacturer;
import com.example.clinicaproject.service.MedicineManufacturerService;
import com.example.clinicaproject.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ModelAndView registrationMedicinePage() {
        //можно выносить в VC config ????
        ModelAndView modelAndView = new ModelAndView();
        List<MedicineManufacturer> manufacturers = medicineManufacturerService.allMedicineManufacturers();
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("registrationMedicine");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView registrationMedicine(@ModelAttribute("medicine") Medicine medicine) {
        ModelAndView modelAndView = new ModelAndView();
        MedicineManufacturer manufacturer = (MedicineManufacturer) httpSession.getAttribute("newManufacturer");
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

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("manufacturer", new MedicineManufacturer());
    }
}
