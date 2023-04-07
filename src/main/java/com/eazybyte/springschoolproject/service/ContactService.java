package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println("Contact bean initialized!");

    }

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
