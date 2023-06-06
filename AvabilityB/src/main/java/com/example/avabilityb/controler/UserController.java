package com.example.avabilityb.controler;


import com.example.avabilityb.model.dto.UserDto;
import com.example.avabilityb.model.entity.UserEntity;
import com.example.avabilityb.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation("Show User In Group")
    @GetMapping("/users/{groupId}")
    public List<UserEntity> getUsersByGroupId(@PathVariable Long groupId) {
        return userService.getUsersByGroupId(groupId);
    }
    @ApiOperation("Show User ")
    @GetMapping("/getuser")
    public UserDto getUsers() {
        return userService.getCurrentUserDto();
    }
    @ApiOperation("Get manager employee")
    @GetMapping("/manageusers")
    public List<UserDto> getUsersByManager() {
        Long userId = userService.getCurrentUserId();
        return userService.getUsersByManagerId(userId);
    }
    @ApiOperation("Show User By Id")
    @GetMapping("/user/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}


