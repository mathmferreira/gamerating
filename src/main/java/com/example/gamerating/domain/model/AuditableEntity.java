package com.example.gamerating.domain.model;

import com.example.gamerating.util.DBConstUtils;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements AbstractEntity {

    @CreatedDate
    @Column(name = DBConstUtils.AUD + "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(name = DBConstUtils.AUD + "created_by", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = DBConstUtils.AUD + "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @LastModifiedBy
    @Column(name = DBConstUtils.AUD + "last_updated_by")
    private String lastUpdatedBy;

}
