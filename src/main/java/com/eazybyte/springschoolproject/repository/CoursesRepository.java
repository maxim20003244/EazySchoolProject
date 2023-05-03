package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findByOrderByNameDesc();
    List<Courses> findByOrderByName();
}
