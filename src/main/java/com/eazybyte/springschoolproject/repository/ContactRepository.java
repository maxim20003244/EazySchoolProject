package com.eazybyte.springschoolproject.repository;


import com.eazybyte.springschoolproject.model.Contact;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface ContactRepository extends CrudRepository<Contact,Integer> {
    List <Contact> findByStatus (String status);


}