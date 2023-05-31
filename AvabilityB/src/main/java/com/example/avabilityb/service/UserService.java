package com.example.avabilityb.service;


import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.GroupRepository;
import com.example.avabilityb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public List<UserEntity> getUsersByGroupId(Long groupId) {
        if (groupRepository.existsById(groupId)) {
            return userRepository.findAllByGroupId(groupId);
        } else {
            throw new IllegalArgumentException("Invalid group ID");
        }
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }
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
    public Long getCurrentUserId() {
        var authenticatedUserName = getCurrentUsername();
        UserEntity userEntity = userRepository.findByMail(authenticatedUserName).orElseThrow(IllegalStateException::new);
        return userEntity.getId();
    }
    public UserEntity getCurrentUserEntity() {
        return userRepository.findById(getCurrentUserId()).get();
    }
}
