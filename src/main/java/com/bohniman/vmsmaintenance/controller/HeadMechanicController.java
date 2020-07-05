package com.bohniman.vmsmaintenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/headmechanic")
public class HeadMechanicController {
    
    // ========================================================================
    // HEADMECHANIC DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageHeadMechanicDashboard(ModelAndView mv) {
        mv = new ModelAndView("headmechanic/dashboard");

        return mv;
    }
}