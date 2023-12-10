package com.youcode.aftas.handler.exception;

import com.youcode.aftas.handler.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OperationsException.class)
    public ResponseEntity<ResponseMessage> operationException(OperationsException ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("erreurs", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
