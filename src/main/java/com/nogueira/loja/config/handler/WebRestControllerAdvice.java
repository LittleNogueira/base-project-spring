package com.nogueira.loja.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorForm> handler(MethodArgumentNotValidException exceptions){
        List<ErrorForm> errors = new ArrayList<>();
        return exceptions.getBindingResult().getFieldErrors().stream().map(ErrorForm::new).collect(Collectors.toList());
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorSimple handler(BadCredentialsException e){
        return new ErrorSimple(e.getMessage());
    }

}
