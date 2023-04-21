package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService  {
    @Autowired
    private  PersonRepository personRepository ;



    public boolean saveMessage(Person person) {
        boolean isSaved = false;
        Person personSave = personRepository.save(person);
        if(null!= personSave && person.getPersonId()>0){
            isSaved=true;
        }
        return isSaved;
    }


}
