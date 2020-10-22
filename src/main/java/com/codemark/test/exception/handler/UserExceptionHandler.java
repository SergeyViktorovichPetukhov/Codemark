package com.codemark.test.exception.handler;

import com.codemark.test.exception.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice()
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    protected ResponseEntity<LocalException> handleNoSuchUserException() {
        List<String> errors = Arrays.asList("no such user");
        return new ResponseEntity<>(
                new LocalException(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<LocalException> handleIllegalArgumentException() {
        List<String> errors = Arrays.asList("wrong user roles");
        return new ResponseEntity<>(
                new LocalException(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleConstraintViolation(RuntimeException ex) {

        ConstraintViolationException constraintViolationException =
                (ConstraintViolationException)ex.getCause().getCause();

        Set<ConstraintViolation<?>> violations = constraintViolationException
                .getConstraintViolations();

        List<String> reasons = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new LocalException(reasons),HttpStatus.BAD_REQUEST);
    }

    private static class LocalException {

        private List<String> errors;

        private boolean success;

        LocalException(List<String> errors){
            this.errors = errors;
        }
        public Object getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}

