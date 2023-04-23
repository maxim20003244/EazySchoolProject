package com.eazybyte.springschoolproject.security;

import com.eazybyte.springschoolproject.model.Person;
import com.eazybyte.springschoolproject.model.Roles;
import com.eazybyte.springschoolproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EazySchollUserNameAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(email);
        if(null!= person && person.getPersonId()>0 && pwd.equals(person.getPwd())){
            return new UsernamePasswordAuthenticationToken(person.getName(), pwd, getGrantedAuthorities(person.getRoles()));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(("ROLE_"+roles.getRoleName())));
        return grantedAuthorities;
    }
}
