package com.codemark.test.controller;

import com.codemark.test.converter.UserConverter;
import com.codemark.test.dto.UserDto;
import com.codemark.test.exception.NoSuchUserException;
import com.codemark.test.model.User;
import com.codemark.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    private UserConverter userConverter;

    public UserRestController(@Autowired UserService userService,
                              @Autowired UserConverter userConverter) {
        this.userConverter = userConverter;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/{login}")
    public ResponseEntity<User> getUser(@PathVariable(name = "login") String login) {
        return new ResponseEntity(userService.getByLogin(login),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{login}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "login") String login) {
        userService.delete(login);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto dto) {
        User user = userService.save(userConverter
                        .convertToModel(
                                dto, dto.getRoles()));

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Location", "/users/" + user.getLogin());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{login}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable (name = "login") String login,
                           @RequestBody UserDto dto) {
        userService.update(login,dto.getRoles());
    }

}
