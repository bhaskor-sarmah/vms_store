package com.bohniman.vmsmaintenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    
    // ========================================================================
    // VENDOR DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageVendorDashboard(ModelAndView mv) {
        mv = new ModelAndView("vendor/dashboard");

        return mv;
    }
}