package com.eazybyte.springschoolproject.repository;
import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List <Contact> findByStatus (String status);

   // @Query("select c from Contact c where c.status = :status")
    @Query(value = "select * from contact_msg  where  status = :status", nativeQuery = true)
    Page<Contact> findByStatusWithQuery( String status , Pageable pageable);

    @Transactional
    @Modifying
    @Query("update  Contact c SET c.status=?1 where c.contactId = ?2")
    int updateMessageById(String status,int id);

    Page<Contact> findOpenMsg(@Param("status") String status,Pageable pageable);
    @Modifying
    @Transactional
    int updateMsgStatus (String status, int id);
    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgNative(@Param("status") String status , Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatusNative (String status, int id);
}