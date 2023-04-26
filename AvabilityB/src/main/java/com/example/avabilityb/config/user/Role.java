package com.example.avabilityb.config.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    EMPLOYEE("Employee"),
    MANAGER("Manager");
    private String role;
}

