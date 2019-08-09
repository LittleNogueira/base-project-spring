package com.nogueira.loja.mapper;

import com.nogueira.loja.form.FormLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public abstract class LoginMapper {

    public static UsernamePasswordAuthenticationToken convertFormLoginForAuthentication(FormLogin formLogin){
        return new UsernamePasswordAuthenticationToken(formLogin.getEmail(),formLogin.getPassword());
    }


}
