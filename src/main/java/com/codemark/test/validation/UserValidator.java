package com.codemark.test.validation;


import com.codemark.test.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if (checkInputString(user.getName())) {
            errors.rejectValue("name", "name.invalid");
        }

        if (checkInputString(user.getLogin())) {
            errors.rejectValue("login", "login.invalid");
        }

        if (checkPassword(user.getPassword())) {
            errors.rejectValue("email", "invalid.invalid");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
    private boolean checkPassword(String input) {
        return (input.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"));
    }
}