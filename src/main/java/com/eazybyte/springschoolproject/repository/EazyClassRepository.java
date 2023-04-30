package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository  extends JpaRepository<EazyClass, Integer> {
}
