package com.example.avabilityb.controler;

import com.example.avabilityb.config.registration.RegistrationFacade;
import com.example.avabilityb.config.registration.dto.RegistrationRequest;
import com.example.avabilityb.config.registration.dto.RegistrationResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController

public class RegisterControler {
    private final RegistrationFacade registrationFacade;

    @ApiOperation("Register to App")
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<RegistrationResponse> registerUser (
            @RequestBody @Valid final RegistrationRequest registrationRequest) {

        RegistrationResponse response = registrationFacade.registerNewUser(registrationRequest);
        return ResponseEntity.ok(response);
    }
}

