package com.codemark.test.dto;

import com.codemark.test.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

public class UserDto {

    public UserDto(){}

    public UserDto(String name, String login, String password, Set<String> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    private String name;

    private String login;

    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder("this is userDto object: \n" +
               this.name + " " + this.login + " " + this.password + "\n roles: \n");
       this.roles.forEach(role -> sb.append(role));
       return sb.toString();
    }
}
