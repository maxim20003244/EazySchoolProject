package com.eazybyte.springschoolproject.controller;

import ch.qos.logback.core.model.Model;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.CoursesRepository;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/displayCourses")
    public ModelAndView displayCourses (Model model, HttpSession session){
        Person person = (Person) session.getAttribute("LoggedInPerson");
        var modelAndView  = new  ModelAndView("courses_enrolled");
        modelAndView.addObject("person",person);
        return modelAndView;

    }
}
