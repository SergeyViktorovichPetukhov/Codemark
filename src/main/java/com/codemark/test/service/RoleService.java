package com.codemark.test.service;

import com.codemark.test.model.Role;
import com.codemark.test.model.User;

import java.util.Set;

public interface RoleService {
    Set<Role> findAll();
    Set<Role> findAllByNames(Set<String> roleNames);
    Role save(Role role);
    Iterable<Role> saveAll(Iterable<Role> roles);
}
