package com.example.avabilityb.config.registration;

import com.example.avabilityb.config.registration.dto.RegistrationRequest;
import com.example.avabilityb.config.registration.dto.RegistrationResponse;
import com.example.avabilityb.config.user.Role;
import com.example.avabilityb.exceptions.UserAlreadyExistsException;
import com.example.avabilityb.model.entity.CalendarEntity;
import com.example.avabilityb.model.entity.GroupEntity;
import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.CalendarRepository;
import com.example.avabilityb.repository.GroupRepository;
import com.example.avabilityb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegistrationFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    private final CalendarRepository calendarRepository;
    public RegistrationResponse registerNewUser(final RegistrationRequest registrationRequest) {

        if (userRepository.existsByMail(registrationRequest.getMail())) {
            throw new UserAlreadyExistsException(String.format("User of username %s already exists",
                    registrationRequest.getMail()));
        }
        UserEntity user = new UserEntity();
        Optional<GroupEntity> group = groupRepository.findById(2l);
        if(group.isEmpty())
            throw new IllegalStateException("Group of id 2 doesn't exist");

        user.setMail(registrationRequest.getMail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setName(registrationRequest.getName());
        user.setSurname(registrationRequest.getSurname());
        user.setPhone(registrationRequest.getPhone());
        user.setRole(Role.EMPLOYEE);
        UserEntity savedUser = userRepository.save(user);
        CalendarEntity calendar = new CalendarEntity();
        calendar.setUser(savedUser);
        calendar.setGroup(group.get());
        calendarRepository.save(calendar);
        return RegistrationResponse.ofUserEntity(savedUser);

    }

}