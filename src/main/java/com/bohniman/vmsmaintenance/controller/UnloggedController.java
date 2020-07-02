package com.bohniman.vmsmaintenance.controller;

import com.bohniman.vmsmaintenance.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class UnloggedController {

    @Autowired
    UserService userService;

    // ========================================================================
    // # PUBLIC INDEX PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView getIndex(ModelAndView mv) {
        mv = new ModelAndView("unlogged/login");

        return mv;
    }
    
}