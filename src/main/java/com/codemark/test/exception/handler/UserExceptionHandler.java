package com.codemark.test.exception.handler;

import com.codemark.test.exception.NoSuchUserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(NoSuchUserException.class)
//    protected ResponseEntity<LocalException> handleNoSuchUserException() {
//        return new ResponseEntity<>(
//                new LocalException("no such user"), HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleConstraintViolations(RuntimeException ex) {
        System.out.println("exception catched");
        return new ResponseEntity<>(
            new LocalException("wrong fields of user"),HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(RuntimeException ex) {
        System.out.println("exception catched");
        return new ResponseEntity<>(
                new LocalException(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    private static class LocalException {

        private String message;

        private boolean success;

        LocalException(String errors){
            this.message = errors;
        }
        public String getErrors() {
            return message;
        }

        public void setErrors(String errors) {
            this.message = errors;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}

