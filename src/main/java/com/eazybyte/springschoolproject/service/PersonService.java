package com.eazybyte.springschoolproject.service;

import com.eazybyte.springschoolproject.constans.EazySchoolConstants;
import com.eazybyte.springschoolproject.model.EazyClass;
import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.model.Roles;
import com.eazybyte.springschoolproject.repository.EazyClassRepository;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import com.eazybyte.springschoolproject.repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService   {
    @Autowired
    private  PersonRepository personRepository ;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = roleRepository.getByRoleName(EazySchoolConstants.ADMIN);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }


}
