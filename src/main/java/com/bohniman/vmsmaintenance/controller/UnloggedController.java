package com.bohniman.vmsmaintenance.controller;

import com.bohniman.vmsmaintenance.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnloggedController {

    @Autowired
    UserService userService;

    // ========================================================================
    // # PUBLIC INDEX PAGE
    // ========================================================================
    @GetMapping(value = { "/", "/login" })
    public ModelAndView getIndex(ModelAndView mv) {
        mv = new ModelAndView("unlogged/login");
        return mv;
    }

    @GetMapping(value = { "/error" })
    public ModelAndView error(ModelAndView mv) {
        mv = new ModelAndView("error");
        return mv;
    }

}