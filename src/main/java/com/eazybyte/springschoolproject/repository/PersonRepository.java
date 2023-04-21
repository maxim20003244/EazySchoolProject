package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,String> {
}
