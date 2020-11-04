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

    public UserConverter(@Autowired ModelMapper modelMapper,
                         @Autowired RoleRepository roleRepository,
                         @Autowired UserRepository userRepository)
    {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public User convertToModel(UserDto dto) {
        Set<String> dtoRoles = dto.getRoles();
        List<Role> allRoles = roleRepository.findAll();

        Set<String> allNamesRoles = allRoles.stream()
                .map(Role::getName).collect(Collectors.toSet());
        if (!allNamesRoles.containsAll(dtoRoles)){
            throw new IllegalArgumentException();}

        Set<Role> userRoles = roleRepository.findAllByNames(dtoRoles);
        User user = new User();
        user.setRoles(userRoles);
        modelMapper.map(dto,user);
        roleRepository.saveAll(userRoles);
        userRepository.save(user);
        return user;
    }

    public UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        modelMapper.map(user,dto);
        Set<String> roles = new HashSet<>();
        Set<Role> userRoles = roleRepository.findByUser(user);
        roles.addAll(userRoles.stream().map(Role::getName).collect(Collectors.toSet()));
        dto.setRoles(roles);
        return dto;
    }

    public List<UserDto> convertAllUsersToDtos(List<User> users) {
        List<UserDto> dtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

}
