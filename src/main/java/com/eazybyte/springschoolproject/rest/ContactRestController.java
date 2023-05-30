package com.eazybyte.springschoolproject.rest;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.Response;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(path = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@RestController
@CrossOrigin(origins = "*")
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
  return null;
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
                .header("isMsg Saved", "true")
                .body(response);
    }
    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg (RequestEntity<Contact> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key,value) -> {
            log.info(String.format("Header '%s' = %s" , key,value.stream().collect(Collectors.joining("|"))));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid contact Id received");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusMsg("Message successfully closed");
        response.setStatusCode("200");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);


    }
}
