package com.nogueira.loja.config.security.filter;

import com.nogueira.loja.model.User;
import com.nogueira.loja.repository.UserRepository;
import com.nogueira.loja.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class TokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public TokenFilter(TokenService tokenService,UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(httpServletRequest);

        if(tokenService.isValid(token)){
            this.authenticatedToken(token);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void authenticatedToken(String token){
        Long id = this.tokenService.getIdUserDetails(token);
        User user = this.userRepository.findById(id).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");

        if(Objects.nonNull(token) && !token.isEmpty() && token.startsWith("Bearer ")){
            return token.substring(7,token.length());
        }

        return null;
    }
}
