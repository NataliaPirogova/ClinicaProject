package com.example.clinicaproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        viewControllerRegistry.addViewController("/healthcareorganizations")
                .setViewName("healthcareorganizations");
        viewControllerRegistry.addViewController("/contacts")
                .setViewName("contactInformationGeneral");
        viewControllerRegistry.addViewController("/error").setViewName("error");
        viewControllerRegistry.addViewController("/login").setViewName("login");
    }
}
