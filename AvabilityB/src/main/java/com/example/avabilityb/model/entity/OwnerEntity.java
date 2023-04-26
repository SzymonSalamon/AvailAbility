package com.example.avabilityb.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Owners")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;

    @OneToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private ManagerEntity manager;
}
