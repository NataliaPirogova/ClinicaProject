package com.example.clinicaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrganizationController {

    @GetMapping(value = "/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/healthcareorganizations", method = RequestMethod.GET)
    public ModelAndView allOrganizations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("healthcareorganizations");
        return modelAndView;
    }

    @GetMapping(value = "/contacts")
    public ModelAndView contactInformationGeneral() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contactInformationGeneral");
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error(ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
