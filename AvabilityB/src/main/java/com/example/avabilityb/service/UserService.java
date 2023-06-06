package com.example.avabilityb.service;


import com.example.avabilityb.model.dto.UserDto;
import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.GroupRepository;
import com.example.avabilityb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDto> getUsersByManagerId(Long userId) {
        if (userRepository.existsById(userId)) {
            UserEntity manager = getUserById(userId);

            return userRepository.findAllByManagerId(manager).stream()
                    .map(this::UserEntityToUserDto)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Invalid manager ID");
        }
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }
    public UserEntity getUserByMail(String mail){
        Optional<UserEntity> user = userRepository.findByMail(mail);
        if(user.isEmpty()){
            throw new IllegalArgumentException("User with this email don't exist");
        }
        return user.get();
    }
    private UserDto UserEntityToUserDto(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .mail(userEntity.getMail())
                .firstName(userEntity.getName())
                .lastName(userEntity.getSurname())
                .build();
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
    public UserDto getCurrentUserDto(){
        return UserEntityToUserDto(getCurrentUserEntity());
    }
}
