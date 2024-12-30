package com.askrindo.authentication.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "M_USER")
public class User extends Auditable<String> implements Serializable {

    @Column(name = "ACCOUNT_EXPIRED",length = 1, nullable = false)
    private Boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED",length = 1, nullable = false)
    private Boolean accountLocked;

    @Column(name = "USERNAME",length = 50, nullable = false)
    private String username;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "FULLNAME",length = 50, nullable = false)
    private String fullname;

    @Column(name = "EMAIL",length = 50, nullable = false)
    private String email;

    @Column(name = "LOGIN_ATTEMP",nullable = false)
    private Integer loginAttemp;

    @Column(name = "IS_ACTIVE",length = 1, nullable = false)
    private Boolean isActive;

    @Column(name = "VERSION",nullable = false)
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private Role role;

}
