package com.codemark.test.controller;

import com.codemark.test.converter.UserConverter;
import com.codemark.test.dto.UserDto;
import com.codemark.test.model.User;
import com.codemark.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return new ResponseEntity<>(userConverter.convertToDtos(userService.getAll()),HttpStatus.OK);
    }

    @GetMapping(value = "/{login}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "login") String login) {
        return new ResponseEntity(userConverter.convertToDto(userService.getByLogin(login)),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{login}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "login") String login) {
        userService.delete(login);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto dto) {
        User user = userService.save(userConverter.convertToModel(dto));
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Location", "/users/" + user.getLogin());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{login}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable (name = "login") String login,
                           @RequestBody UserDto dto) {
        userService.update(userConverter.convertToModel(dto));
    }

}
