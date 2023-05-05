package com.eazybyte.springschoolproject.controller;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.Courses;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/displayMessages/page/{pageNum}" , method = RequestMethod.GET)
    public ModelAndView displayMessage(Model model,
                                       @PathVariable(name = "pageNum")int pageNum,@RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir){
       Page<Contact> msgPage = contactService.findMessageWithOpenStatus(pageNum,sortField,sortDir);
       List<Contact> contactMsg = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages");
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs",contactMsg);
        return modelAndView;
    }


    @RequestMapping("/displayUsers")
  public ModelAndView displayUsers(Model model ){
      List<Person> usersFind = personRepository.findAll();
      ModelAndView modelAndView = new ModelAndView("users");
      modelAndView.addObject("people", usersFind);
      return modelAndView;
  }

    @GetMapping("/deleteUsers")
    public ModelAndView deleteStudentFromCourses (Model model , @RequestParam int personId){
        ModelAndView modelAndView = new ModelAndView("redirect:/displayUsers");
        if( personId != 2202){
            personRepository.deleteById(personId);
          return modelAndView;
      }
        return modelAndView;
    }
@RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg (@RequestParam int id ) {
    contactService.updateMsgStatus(id);
    return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
}




}
