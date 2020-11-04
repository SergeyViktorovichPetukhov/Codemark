package com.codemark.test.service.implementation;

import com.codemark.test.converter.UserConverter;
import com.codemark.test.dto.UserDto;
import com.codemark.test.exception.NoSuchUserException;
import com.codemark.test.model.User;
import com.codemark.test.repository.UserRepository;
import com.codemark.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private UserConverter userConverter;

    public UserServiceImpl(@Autowired UserRepository repository,
                           @Autowired UserConverter userConverter){
        this.repository = repository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAllWithoutRoles();
        if (users != null){
            return userConverter.convertAllUsersToDtos(users);
        }
        return null;
    }


    @Override
    public UserDto get(String login) {
        return userConverter.convertToDto(findByLogin(login));
    }

    @Override
    public User save(UserDto dto) {
        return userConverter.convertToModel(dto);
    }

    @Override
    public void delete(String login) {
        User user = findByLogin(login);
        repository.delete(user);
    }

    @Override
    public void update(String login, UserDto dto) {
        User existing = findByLogin(login);
        if (existing != null){
            userConverter.convertToModel(dto);
        }
    }

    private User findByLogin(String login) {
        Optional<User> user = repository.findByLogin(login);
        return user.orElseThrow(() -> new NoSuchUserException());
    }

}
