package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.Mail;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
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
    public void sendNotification(Mail maill)throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(contactService.saveMsg(maill));
        mail.setFrom("maxim200003244@gmail.com");
        mail.setSubject("no-reply");
        mail.setText("This message to subscribe to our school! ");
        javaMailSender.send(mail);

    }
}
