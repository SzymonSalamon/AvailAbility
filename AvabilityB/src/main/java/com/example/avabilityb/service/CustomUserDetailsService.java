package com.example.avabilityb.service;

import com.example.avabilityb.config.user.CustomUser;
import com.example.avabilityb.config.user.UserEntity;

import com.example.avabilityb.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByMail(username);
        if(user.isPresent()){
            return user.get().asCustomUser();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
