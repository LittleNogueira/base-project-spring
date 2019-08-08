package com.nogueira.loja.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter @Setter
public abstract class BaseDTO {

    protected Calendar created;
    protected Calendar udpated;

}
