package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.model.Profile;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class ProfileController {
@RequestMapping("/display")
    public ModelAndView displayMessages(Model model, HttpSession session){
    Person person = (Person) session.getAttribute("LoggedInPerson");
    Profile profile = new Profile();
    profile.setName(person.getName());
    profile.setMobileNumber(person.getMobileNumber());
    profile.setEmail(person.getEmail());

    if(person.getAddress() != null && person.getAddress().getAddressId()>0){
        profile.setAddress1(person.getAddress().getAddress1());
        profile.setAddress2(person.getAddress().getAddress2());
        profile.setCity(person.getAddress().getCity());
        profile.setState(person.getAddress().getState());
        profile.setZipCode(person.getAddress().getZipCode());
    }
    ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile",profile);
        return modelAndView;

    }
}
