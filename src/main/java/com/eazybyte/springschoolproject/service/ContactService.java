package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {


    public boolean saveMessage(Contact contact){
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
    public String saveMessageString (Contact contact){
        String save ;
        save = contact.toString();
        return save;
    }


}
