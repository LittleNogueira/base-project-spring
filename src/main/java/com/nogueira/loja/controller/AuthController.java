package com.nogueira.loja.controller;

import com.nogueira.loja.dto.TokenDTO;
import com.nogueira.loja.form.FormLogin;
import com.nogueira.loja.mapper.LoginMapper;
import com.nogueira.loja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid FormLogin formLogin){
         Authentication authentication = authenticationManager.authenticate(LoginMapper.convertFormLoginForAuthentication(formLogin));
        return ResponseEntity.ok(new TokenDTO(tokenService.generateToken(authentication),"Bearer"));
    }

}
