package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.config.EazySchoolProps;
import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Contact;
import com.eazybyte.springschoolproject.model.Mail;
import com.eazybyte.springschoolproject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;

import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {

    private final ContactRepository contactRepository;
    private final EazySchoolProps eazySchoolProps;

    public ContactService(ContactRepository contactRepository, EazySchoolProps eazySchoolProps) {
        this.contactRepository = contactRepository;
        this.eazySchoolProps = eazySchoolProps;
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
    public String saveMsg (Mail mail){
        String save;
        save= mail.getMail();
        return save;
    }
    public Page<Contact> findMessageWithOpenStatus(int pageNum , String sortField, String sortDir){
        int pageSize = eazySchoolProps.getPageSize();
        if(null!= eazySchoolProps.getContact() && null!=eazySchoolProps.getContact().get("pageSize")){
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sortDir.equals("asc") ? Sort.by(sortField).ascending()
                       : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(EazySchoolConstants.OPEN,pageable);
        return msgPage;
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
        int rows  = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE,contactId);
        if(rows> 0){
            isUpdate = true;
        }
        return isUpdate;
    }

}


