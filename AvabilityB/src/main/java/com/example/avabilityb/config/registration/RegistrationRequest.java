package com.example.avabilityb.config.registration;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class RegistrationRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Integer phone;
}