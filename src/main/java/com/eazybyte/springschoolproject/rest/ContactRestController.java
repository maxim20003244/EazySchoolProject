package com.eazybyte.springschoolproject.rest;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.Response;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/api/contact")
@RestController
public class ContactRestController {

    private final ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @GetMapping("/getMessagesByStatus")
   // @ResponseBody
    public List<Contact> getMessageByStatus(@RequestParam(name = "status")String status){
        return contactRepository.findByStatus(status);
    }
    @GetMapping("/getAllMessagesByStatus")
    //@ResponseBody
    public List<Contact> getAllMsgsBYStatus(@RequestBody Contact contact){
  if(null!= contact && null!= contact.getStatus() ){
      return  contactRepository.findByStatus(contact.getStatus());
  }
  return List.of();
    }

    @PostMapping("/saveMsg")
    // @ResponseBody
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
                                            @Valid @RequestBody Contact contact){
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }
}
