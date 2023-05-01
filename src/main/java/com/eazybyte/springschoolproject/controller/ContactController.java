package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.EazyClass;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import com.eazybyte.springschoolproject.service.ContactService;


import com.eazybyte.springschoolproject.service.PersonService;
import com.eazybyte.springschoolproject.service.SendMailService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    private final ContactService contactService;
    private final SendMailService service;
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final EazyClassRepository eazyClassRepository;




  private static Logger log = LoggerFactory.getLogger(ContactController.class);

  @Autowired
    public ContactController(ContactService contactService, SendMailService service, PersonRepository personRepository, PersonService personRepository1, PersonRepository personRepository2, EazyClassRepository eazyClassRepository) {
        this.contactService = contactService;


      this.service = service;


      this.personService = personRepository1;
      this.personRepository = personRepository2;
      this.eazyClassRepository = eazyClassRepository;
  }

    @RequestMapping("/contact")
    public String displayContactPAge(Model model){
       model.addAttribute("contact" ,new Contact());
      return "contact";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()){
            log.error("Contact form validations failed due to: " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessage(contact);

        return"redirect:/contact" ;

    }


    @RequestMapping("/sendMessage")
    public String sendMail(Contact contact){
        service.sendNotification(contact);
        return "redirect:/contact";
    }
    @RequestMapping(value = "/displayMessages" , method = RequestMethod.GET)
    public ModelAndView displayMessage(Model model){
       List<Contact> contacts = contactService.findMessageWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("contact",contacts);
        return modelAndView;
    }

    @RequestMapping("/displayUsers")
  public ModelAndView displayUsers(Model model ){
      List<Person> users = personService.findAllUsers();
      ModelAndView modelAndView = new ModelAndView("users");
      modelAndView.addObject("people", users);
      return modelAndView;
  }

@RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg (@RequestParam int id ) {
    contactService.updateMsgStatus(id);
    return "redirect:/displayMessages";
}





}
