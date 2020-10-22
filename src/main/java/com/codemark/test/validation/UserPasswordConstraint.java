package com.codemark.test.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UserValidator.class)
public @interface UserPasswordConstraint {
    String message() default "password is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
