package com.nogueira.loja.handler;

import org.springframework.validation.FieldError;

public class ErrorForm {
    public String field;
    public String message;

    public ErrorForm(FieldError fieldError){
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
