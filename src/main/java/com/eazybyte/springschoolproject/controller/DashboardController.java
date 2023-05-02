package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {
    @Autowired
    private PersonRepository personRepository;
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username",person.getName());
        model.addAttribute("roles",authentication.getAuthorities(  ).toString());
        if(null!= person.getEazyClass() && null!= person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName( ));
        }
        session.setAttribute("LoggedInPerson",person);
        return "dashboard.html";

    }
}
