package com.codemark.test.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @NonNull
    @Column
    @NotBlank(message = "invalid name")
    private String name;

    @NonNull
    @Id
    @Column(name = "login", nullable = false, unique = true)
    @NotBlank(message = "invalid login")
    private String login;

    @NonNull
    @Column
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{1,}$",
             message = "invalid password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_login") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash( login, name);
    }

    @Override
    public String toString(){
        return "user: " + this.getName() + " " + this.getLogin() + " " + this.getRoles().toString();
    }
}
