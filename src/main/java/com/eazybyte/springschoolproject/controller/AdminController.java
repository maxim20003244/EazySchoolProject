package com.eazybyte.springschoolproject.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
@RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("classes");
        return modelAndView;

    }
}
