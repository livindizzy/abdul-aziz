package com.askrindo.authentication.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "M_LOOKUP")
public class Lookup implements Serializable {

    @Id
    @Column(name = "LOOKUP_KEY",length = 150, nullable = false)
    private String lookupKey;

    @Column(name = "LOOKUP_GROUP",length = 50, nullable = false)
    private String lookupGroup;

    @Column(name = "KEY_ONLY",length = 50, nullable = false)
    private String keyOnly;

    @Column(name = "LABEL",nullable = false)
    private String label;

    @Column(name = "VERSION",nullable = false)
    private Integer version;

    @Column(name = "IS_ACTIVE",length = 1, nullable = false)
    private Boolean isActive;

    @CreatedBy
    @Column(name = "created_by", length = 100)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    protected String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

}