package com.askrindo.authentication.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "M_ROLE")
public class Role extends Auditable<String> implements Serializable {

    @Column(name = "ROLE_NAME",length = 20, nullable = false)
    private String roleName;

    @Column(name = "KETERANGAN",nullable = false)
    private String keterangan;

    @Column(name = "IS_ACTIVE",length = 1, nullable = false)
    private Boolean isActive;

    @Column(name = "VERSION",nullable = false)
    private Integer version;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private Set<User> Users = new HashSet<>();

}
