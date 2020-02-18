package com.nouhoun.springboot.jwt.integration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super();
    }
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProjectNotFoundException(String message) {
        super(message);
    }
    public ProjectNotFoundException(Throwable cause) {
        super(cause);
    }
}