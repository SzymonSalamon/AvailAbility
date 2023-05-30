package com.example.avabilityb.config.registration.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class RegistrationRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    private String password;

    private Integer phone;
}