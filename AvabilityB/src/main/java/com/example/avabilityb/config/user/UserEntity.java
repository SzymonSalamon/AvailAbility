package com.example.avabilityb.config.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenertor"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled = true;
    @Column(name="role")
    private Role role;

    public CustomUser asCustomUser(){
        return new CustomUser(this);
    }
}