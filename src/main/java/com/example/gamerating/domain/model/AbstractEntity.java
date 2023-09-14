package com.example.gamerating.domain.model;

import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Objects;

public interface AbstractEntity extends Persistable<Long>, Serializable {

    void setId(Long id);

    @Override
    @Transient
    default boolean isNew() {
        return Objects.isNull(getId());
    }

}
