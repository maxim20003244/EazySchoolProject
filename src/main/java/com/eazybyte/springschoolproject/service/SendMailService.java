package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class SendMailService {
    private final JavaMailSender javaMailSender;
    private final  ContactService contactService;


    public SendMailService(JavaMailSender javaMailSender, ContactService contactService) {
        this.javaMailSender = javaMailSender;
        this.contactService = contactService;
    }
    public void sendNotification(Contact contact){
        SimpleMailMessage mail = new SimpleMailMessage();
        //mail.setTo(cotact.getEmail());
        mail.setTo("maxim.rotar2000@mail.ru");
        //mail.setFrom("maxim.rotar2000@mail.ru");
        mail.setSubject("Test subject");
        mail.setText(contactService.saveMessageString(contact));
        javaMailSender.send(mail);
    }
}
