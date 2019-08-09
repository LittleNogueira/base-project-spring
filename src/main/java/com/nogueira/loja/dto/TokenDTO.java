package com.nogueira.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TokenDTO {

    private String token;
    private String type;

}
