package com.bohniman.vmsmaintenance.controller;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // ========================================================================
    // USER DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView getIndex(ModelAndView mv) {
        mv = new ModelAndView("user/dashboard");

        return mv;
    }

}