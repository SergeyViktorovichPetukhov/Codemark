package com.codemark.test.repository;

import com.codemark.test.model.Role;
import com.codemark.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Set<Role> findByUser(User user);
    @Query("SELECT r FROM Role r WHERE r.name IN :rNames")
    Set<Role> findAllByNames(@Param(value = "rNames") Set<String> roleNames);

}
