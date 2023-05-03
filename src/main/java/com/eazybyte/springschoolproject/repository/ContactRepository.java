package com.eazybyte.springschoolproject.repository;
import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List <Contact> findByStatus (String status);
    Page<Contact> findByStatus(String status , Pageable pageable);
}