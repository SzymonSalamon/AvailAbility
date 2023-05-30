package com.example.avabilityb.service;

import com.example.avabilityb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

;
@RequiredArgsConstructor
public class AuthorizedUserFacade {
    private final UserRepository userRepository;
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        }
        return username;
    }
    public long getCurrentUserId() {
        var authenticatedUserName = getCurrentUsername();
        var userEntity = userRepository.findByMail(authenticatedUserName).orElseThrow(IllegalStateException::new);
        return userEntity.getId();
    }
}