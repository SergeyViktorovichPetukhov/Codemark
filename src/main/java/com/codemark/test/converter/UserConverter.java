package com.codemark.test.converter;

import com.codemark.test.dto.UserDto;
import com.codemark.test.model.Role;
import com.codemark.test.model.User;
import com.codemark.test.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    private ModelMapper modelMapper;
    private RoleRepository roleRepository;

    public UserConverter(@Autowired ModelMapper modelMapper,
                         @Autowired RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public User convertToModel(UserDto dto) {
        User user = new User();
        modelMapper.map(dto,user);
        user.setRoles(dto.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName))
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
