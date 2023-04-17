package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        contact.setCreateBY(EazySchoolConstants.ANONYMOUS);
        contact.setCreateAt(LocalDateTime.now());
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

        public Object findById(int id){

        Object contacts = contactRepository.findById(id);
        return  contacts;
        }



        public boolean updateMsgStatus(int contactId, String updateBy){
        boolean isUpdate =false;
            Optional<Contact> contact = contactRepository.findById(contactId);
            contact.ifPresent(contact1 -> {
                contact1.setStatus(EazySchoolConstants.CLOSE);
                contact1.setUpdateBy(updateBy);
                contact1.setUpdateAt(LocalDateTime.now());
            });
            Contact updateContact = contactRepository.save(contact.get());
            if(null!= updateContact && updateContact.getUpdateBy()!=null){
                isUpdate = true;
            }
        return isUpdate;
        }
    }



