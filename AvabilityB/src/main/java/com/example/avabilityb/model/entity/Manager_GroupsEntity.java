package com.example.avabilityb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Managers_Groups")
public class Manager_GroupsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

}