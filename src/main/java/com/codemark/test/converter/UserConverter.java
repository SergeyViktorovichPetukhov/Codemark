package com.codemark.test.converter;

import com.codemark.test.dto.UserDto;
import com.codemark.test.model.Role;
import com.codemark.test.model.User;
import com.codemark.test.repository.RoleRepository;
import com.codemark.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    public UserConverter(@Autowired ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    public User convertToModel(UserDto dto, Set<String> userRoles) {

        Set<String> allNamesRoles = new HashSet<>(userRoles);

        if (!allNamesRoles.containsAll(dto.getRoles()))
            throw new IllegalArgumentException();

        User user = new User();
        modelMapper.map(dto,user);
        user.setRoles(userRoles.stream()
                .map(roleName -> new Role(roleName))
                .collect(Collectors.toSet()));
        return user;
    }

    public UserDto convertToDto(User user) {

        UserDto dto = new UserDto();
        modelMapper.map(user,dto);
        Set<String> roles = new HashSet<>(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        dto.setRoles(roles);
        return dto;
    }

    public List<UserDto> convertToDtos(List<User> users) {
        List<UserDto> dtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

}
