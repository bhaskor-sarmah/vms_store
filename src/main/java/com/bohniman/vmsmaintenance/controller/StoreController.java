package com.bohniman.vmsmaintenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/store")
public class StoreController {


    // ========================================================================
    // STORE DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageStoreDashboard(ModelAndView mv) {
        mv = new ModelAndView("store/dashboard");

        return mv;
    }

    // ========================================================================
    // VEHICLE SEARCH PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle")
    public ModelAndView pageVehicleSearch(ModelAndView mv) {
        mv = new ModelAndView("store/vehicle_search");

        return mv;
    }

    // ========================================================================
    // VEHICLE DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}")
    public ModelAndView pageVehicleDetail(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/vehicle_detail");
        mv.addObject("vehicleId", vehicleId);
        return mv;
    }

    // ========================================================================
    // INVENTORY PAGE
    // ========================================================================
    @GetMapping(value = "/inventory")
    public ModelAndView pageInventory(ModelAndView mv) {
        mv = new ModelAndView("store/inventory");

        return mv;
    }

}