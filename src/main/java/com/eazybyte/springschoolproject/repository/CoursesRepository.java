package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path = "courses")

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findByOrderByNameDesc();
    List<Courses> findByOrderByName();
}
