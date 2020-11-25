package com.codemark.test.service.implementation;

import com.codemark.test.exception.NoSuchUserException;
import com.codemark.test.model.User;
import com.codemark.test.repository.UserRepository;
import com.codemark.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(@Autowired UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        List<String> usersData = repository.findAllWithoutRoles();
        if (usersData != null){
            List<User> result = new ArrayList<>();
            usersData.forEach(s -> result.add(
                    new User(
                        s.split(",")[0],
                        s.split(",")[1],
                        s.split(",")[2]

            )));
            return result;
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        return findByLogin(login);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(String login) {
        User user = findByLogin(login);
        repository.delete(user);
    }

    @Override
    public void update(User user) {
        User existing = findByLogin(user.getLogin());
        if (existing != null){
            save(user);
        }
        else throw new NoSuchUserException();
    }

    private User findByLogin(String login) {
        Optional<User> user = repository.findByLogin(login);
        return user.orElse(null);
    }

}
