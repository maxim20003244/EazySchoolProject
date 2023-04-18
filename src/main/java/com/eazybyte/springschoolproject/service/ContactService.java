package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        Contact saveContact  = contactRepository.save(contact);
        if(null!= saveContact && saveContact.getContactId()>0){
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
        List<Contact> contactMsgs= contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }
    public List<Contact> getAll(){
        return (List<Contact>) contactRepository.findAll();
    }

    public Contact findById(int contactId){
        var contact = new Contact();
        Contact contacts = contactRepository.findById(contactId).get();
        return  contacts;
    }



    public boolean updateMsgStatus(int contactId){
        boolean isUpdate =false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        });
        Contact updateContact = contactRepository.save(contact.get());
        if(null!= updateContact && updateContact.getUpdateBy()!=null){
            isUpdate = true;
        }
        return isUpdate;
    }
}


