package com.codemark.test.service;

import com.codemark.test.dto.UserDto;
import com.codemark.test.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(String login);
    User save(UserDto dto);
    void delete(String login);
    void update(String login, UserDto user);
}
