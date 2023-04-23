package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.model.Roles;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import com.eazybyte.springschoolproject.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService  {
    @Autowired
    private  PersonRepository personRepository ;
    @Autowired
    private RoleRepository roleRepository;



    public boolean createNewPerson(Person person){
         boolean isSaved = false;
        Roles role = roleRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }


}
