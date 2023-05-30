package com.example.avabilityb.service;


import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.repository.GroupRepository;
import com.example.avabilityb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
