package com.practice.boot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value=RecordExist.class)
    public ResponseEntity recordExistException(RecordExist recordExist) {
        return new ResponseEntity(recordExist.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=RecordNotFound.class)
    public ResponseEntity recordNotFoundException(RecordNotFound recordNotFound) {
        return new ResponseEntity(recordNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity invalidCredException(InvalidCredentialsException invalidCredentialsException) {
        return new ResponseEntity(invalidCredentialsException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity expired(TokenExpiredException expiredJwtException) {
        return new ResponseEntity(expiredJwtException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
