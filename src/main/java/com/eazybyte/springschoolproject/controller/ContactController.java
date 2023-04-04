package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.service.ContactService;


import com.eazybyte.springschoolproject.service.SendMailService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    private final ContactService contactService;
    private final SendMailService service;



  private static Logger log = LoggerFactory.getLogger(ContactController.class);

  @Autowired
    public ContactController(ContactService contactService, SendMailService service) {
        this.contactService = contactService;


      this.service = service;
  }

    @RequestMapping("/contact")
    public String displayContactPAge(Model model){
       model.addAttribute("contact" ,new Contact());
      return "contact";
    }
/*@RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/contact");
    }*/
    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        contactService.saveMessage(contact);
        if(errors.hasErrors()){
            log.error("Contact form validations failed due to: " + errors.toString());
            return "contact.html";
        }
        System.out.println(contactService.saveMessageString(contact));
        return"redirect:/contact" ;

    }


    @RequestMapping("/sendMessage")
    public String sendMail(Contact contact){
        service.sendNotification(contact);
        return "redirect:/contact";
    }


}
