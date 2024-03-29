package com.example.avabilityb.config.user;

import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User of username %s cannot be found", username)));
        return new CustomUser(userEntity);
    }
}
