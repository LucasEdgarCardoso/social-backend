package com.nulo.social.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerImpl {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Error> handleServiceException(ServiceException ex) {
    	log.error("Erro: ", ex);
        return ResponseEntity.status(ex.getErrorCategory()).body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Errors>> handle400Error(MethodArgumentNotValidException ex) {
    	log.error("Erro: ", ex);
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(Errors::new).collect(Collectors.toList()));
    }

    private record Errors(String field, String message) {
        public Errors(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    
    private record Error(String erro) {}

}
