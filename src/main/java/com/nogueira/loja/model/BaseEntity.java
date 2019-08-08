package com.nogueira.loja.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;

    @PrePersist
    protected void prePersist(){
        this.created = Calendar.getInstance();
        this.updated = Calendar.getInstance();
    }

    @PreUpdate
    protected void preUpdate(){
        this.updated = Calendar.getInstance();
    }
}
