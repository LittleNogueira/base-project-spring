package com.nogueira.loja.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class FormLogin {

    @NotNull
    @Email
    private String email;

    @NotEmpty
    private String password;

}
