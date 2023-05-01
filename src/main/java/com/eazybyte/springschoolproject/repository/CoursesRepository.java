package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
