package com.codemark.test.service;

import com.codemark.test.dto.UserDto;
import com.codemark.test.model.Role;
import com.codemark.test.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    List<User> getAll();
    User getByLogin(String login);
    User save(User user);
    void delete(String login);
    void update( User user);
}
