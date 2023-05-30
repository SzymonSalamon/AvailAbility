package com.example.avabilityb.model.entity;

import com.example.avabilityb.config.user.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "surname")
    private String surname;
    @NotNull
    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private Integer phone;
    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name="role")
    private Role role;

}
