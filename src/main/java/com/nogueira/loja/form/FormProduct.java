package com.nogueira.loja.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter
public class FormProduct {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal value;

    @NotNull
    @Size(max = 255, min = 20)
    private String description;

}
