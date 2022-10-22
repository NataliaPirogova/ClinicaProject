package com.example.clinicaproject.controller;

import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private VolunteerService userService;

    @GetMapping("/registrationV")
    public String registration(Model model) {
        model.addAttribute("userForm", new Volunteer());
        return "registration";
    }

    @PostMapping("/registrationV")
    public String addUser(@RequestParam String email,
                          @RequestParam String password,
                          @ModelAttribute("userForm") Volunteer userForm,
                          BindingResult bindingResult,
                          Model model) {
        Volunteer v = new Volunteer();
        v.setPassword(password);
        v.setEmail(email);
        userService.addVolunteer(v);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
//        if (!userService.addVolunteer(userForm)) {
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "registration";
//        }

        return "redirect:/registerVolunteer";
    }
}
