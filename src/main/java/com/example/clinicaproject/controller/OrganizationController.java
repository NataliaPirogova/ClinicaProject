package com.example.clinicaproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrganizationController {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);


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


}
