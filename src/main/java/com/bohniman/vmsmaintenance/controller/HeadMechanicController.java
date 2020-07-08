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

    // ========================================================================
    // PAGE PENDING JOB CARD
    // ========================================================================
    @GetMapping(value = "/pending-job-card")
    public ModelAndView pendingJobCard(ModelAndView mv) {
        mv = new ModelAndView("headmechanic/pending_job_card");
        return mv;
    }

    // ========================================================================
    // PAGE ARCHIVED JOB CARD
    // ========================================================================
    @GetMapping(value = "/archived-job-card")
    public ModelAndView archivedJobCard(ModelAndView mv) {
        mv = new ModelAndView("headmechanic/archived_job_card");
        return mv;
    }
}