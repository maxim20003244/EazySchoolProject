package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles getByRoleName(String roleName);
}
