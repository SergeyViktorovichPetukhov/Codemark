package com.codemark.test.repository;

import com.codemark.test.model.Role;
import com.codemark.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    @Query(value = "SELECT name, login, password FROM users;", nativeQuery = true)
    List<String> findAllWithoutRoles();
}
