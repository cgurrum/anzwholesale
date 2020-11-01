package com.anz.wholesale.exception;

import com.anz.wholesale.exception.models.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException exception) {
        log.error("AuthenticationException: ", exception);
        return new ResponseEntity<>(ErrorMessage.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException exception) {
        log.error("AccessDeniedException: ", exception);
        return new ResponseEntity<>(ErrorMessage.of("Access Denied"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        log.error("Exception: ", exception);
        return new ResponseEntity<>(ErrorMessage.of("Something is not right with the request"), HttpStatus.BAD_REQUEST);
    }

}
