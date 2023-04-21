package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String displayRegisterPage (Model model){
        model.addAttribute("person", new Person());
        return "register";
    }
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("person")Person person, Errors errors){
       if(errors.hasErrors()){
           return "register";
       }
       return "redirect:/login?register=true";
    }
    @RequestMapping(value = "/saveMessages", method = RequestMethod.POST)
    public  String savePerson (@Valid @ModelAttribute("person") Person person,Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validations failed due to: " + errors.toString());
            return "register";
        }
        personService.saveMessage(person);
        return "redirect:/login?register=true";
    }
}
