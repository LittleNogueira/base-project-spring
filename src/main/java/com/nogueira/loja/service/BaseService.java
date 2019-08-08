package com.nogueira.loja.service;

import com.nogueira.loja.exception.NotFoundException;

import java.util.Optional;

abstract class BaseService {

    void verifyErroNotFound(Optional<?> optional){
        if(!optional.isPresent()){
            throw new NotFoundException("Error not found");
        }
    }
}
