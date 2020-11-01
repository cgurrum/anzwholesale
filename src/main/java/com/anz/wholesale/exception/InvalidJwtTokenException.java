package com.anz.wholesale.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtTokenException extends AuthenticationException {
    public InvalidJwtTokenException(String msg) {
        super(msg);
    }
}
