package com.example.avabilityb.config.registration;

import com.example.avabilityb.config.user.UserEntity;
import java.util.UUID;

public record RegistrationResponse(UUID uuid, String username) {
    public static RegistrationResponse ofUserEntity(UserEntity userEntity) {
        return new RegistrationResponse(userEntity.getId(), userEntity.getUsername());
    }
}
