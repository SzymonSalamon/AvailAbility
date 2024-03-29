package com.example.avabilityb.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
}

