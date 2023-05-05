package com.eazybyte.springschoolproject.rest;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/api/contact")
public class ContactRestController {

    private final ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @GetMapping("/getMessagesByStatus")
    @ResponseBody
    public List<Contact> getMessageByStatus(@RequestParam(name = "status")String status){
        return contactRepository.findByStatus(status);
    }
}
