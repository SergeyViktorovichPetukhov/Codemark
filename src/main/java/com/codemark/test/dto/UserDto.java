package com.codemark.test.dto;

import com.codemark.test.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @NonNull
    private String name;
    @NonNull
    private String login;
    @NonNull
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<String> roles;

    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder("this is userDto object: \n" +
               this.name + " " + this.login + " " + this.password + "\n roles: \n");
       this.roles.forEach(role -> sb.append(role));
       return sb.toString();
    }
}
