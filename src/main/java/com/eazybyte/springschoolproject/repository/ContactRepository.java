package com.eazybyte.springschoolproject.repository;
import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List <Contact> findByStatus (String status);

   // @Query("select c from Contact c where c.status = :status")
    @Query(value = "select * from contact_msg  where  status = :status", nativeQuery = true)
    Page<Contact> findByStatus(String status , Pageable pageable);
}