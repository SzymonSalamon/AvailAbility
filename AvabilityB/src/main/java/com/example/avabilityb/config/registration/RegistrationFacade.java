package com.example.avabilityb.config.registration;

import com.example.avabilityb.config.registration.dto.RegistrationRequest;
import com.example.avabilityb.config.registration.dto.RegistrationResponse;
import com.example.avabilityb.exceptions.UserAlreadyExistsException;
import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationResponse registerNewUser(final RegistrationRequest registrationRequest) {

        if (userRepository.existsByMail(registrationRequest.getMail())) {
            throw new UserAlreadyExistsException(String.format("User of username %s already exists",
                    registrationRequest.getMail()));
        }
        UserEntity user = new UserEntity();
        user.setMail(registrationRequest.getMail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setName(registrationRequest.getName());
        user.setSurname(registrationRequest.getSurname());
        user.setPhone(registrationRequest.getPhone());
        UserEntity savedUser = userRepository.save(user);

        return RegistrationResponse.ofUserEntity(savedUser);

    }

}