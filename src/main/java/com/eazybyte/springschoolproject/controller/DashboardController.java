package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {
    private final PersonRepository personRepository;
    private final Environment environment;
    @Value("${eazyschool.pageSize}")
    private int defaultPageSize;

    @Value("${eazyschool.contact.successMessages}")
    private String messages;

    public DashboardController(PersonRepository personRepository, Environment environment) {
        this.personRepository = personRepository;
        this.environment = environment;
    }

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username",person.getName());
        model.addAttribute("roles",authentication.getAuthorities(  ).toString());
        if(null!= person.getEazyClass() && null!= person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName( ));
        }
        session.setAttribute("LoggedInPerson",person);
        logMessages();
        return "dashboard.html";

    }
    private  void logMessages(){
        log.error("Error messages from Dashboard page");
        log.warn("Warn messages from Dashboard page");
        log.info("Info messages from Dashboard page");
        log.trace("Trace messages from Dashboard page");
        log.trace("Debug messages from Dashboard page");

        log.error("defaultPageSize value with @Value annotation is : " + defaultPageSize);
        log.error("successMsg value with @Value annotation is : " + messages);

        log.error("defaultPage size value with Environment is : " + environment.getProperty("eazuschool.pageSize"));
        log.error("successMsg with Environment is : " + environment.getProperty("eazyschool.contact.successMessages"));
        log.error("Java home rnvironment variable using Environment is : " + environment.getProperty("JAVA_HOME"));
    }
}
