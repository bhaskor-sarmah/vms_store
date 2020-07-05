package com.bohniman.vmsmaintenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/mtoadmin")
public class MtoAdminController {
    
    // ========================================================================
    // MTOADMIN DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageMtoAdminDashboard(ModelAndView mv) {
        mv = new ModelAndView("mtoadmin/dashboard");

        return mv;
    }
}