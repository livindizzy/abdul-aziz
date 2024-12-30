package com.askrindo.authentication.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -9037606160613198635L;

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36, nullable = false)
    private UUID id;

    @CreatedBy
    @Column(name = "created_by", length = 70)
    protected T createdBy;

    @CreatedDate
    @Column(name = "created_date")
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_by", length = 70)
    protected T updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

}
