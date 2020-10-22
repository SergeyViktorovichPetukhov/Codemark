package com.codemark.test.service.implementation;

import com.codemark.test.model.Role;
import com.codemark.test.model.User;
import com.codemark.test.repository.RoleRepository;
import com.codemark.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    public RoleServiceImpl(@Autowired RoleRepository repository){
        this.repository = repository;
    }

    @Override
    public Set<Role> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<Role> findAllByNames(Set<String> roleNames) {
        return repository.findAllByNames(roleNames);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Iterable<Role> saveAll(Iterable<Role> roles) {
        return repository.saveAll(roles);
    }
}
