package com.example.avabilityb.config.registration.dto;

import com.example.avabilityb.model.entity.UserEntity;

public record RegistrationResponse(Long uuid, String username) {
    public static RegistrationResponse ofUserEntity(UserEntity userEntity) {
        return new RegistrationResponse(userEntity.getId(), userEntity.getMail());
    }
}
