package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value={"","/","home"})
    public String displayHomePage() {
        return "home.html";
    }

}
