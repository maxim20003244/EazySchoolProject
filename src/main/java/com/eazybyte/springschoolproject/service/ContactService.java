package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact bean initialized!");

    }

    public boolean saveMessage(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreateBY(EazySchoolConstants.ANONYMOUS);
        contact.setCreateAt(LocalDateTime.now());
        int result = contactRepository.saveContactMessage(contact);
        if(result>=0){
            isSaved=true;
        }
        return isSaved;
    }
    public String saveMessageString (Contact contact) {
        String save;
        save = contact.toString();
        return save;
    }
        public List<Contact> findMessageWithOpenStatus(){
            List<Contact> contactMsgs= contactRepository.findMessagesWithStatus(EazySchoolConstants.OPEN);
            return contactMsgs;
        }
        public List<Contact> getAll(){
        return  contactRepository.findAll();
        }

        public boolean updateMsgStatus(int contactId, String updateBy){
        boolean isUpdate =false;
        int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updateBy);
        if(result>0){
            isUpdate=true;
        }
        return isUpdate;
        }
    }



