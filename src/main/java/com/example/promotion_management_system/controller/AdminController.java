package com.example.promotion_management_system.controller;

import com.example.promotion_management_system.model.user.UserRequestDTO;
import com.example.promotion_management_system.model.user.UserResponseDTO;
import com.example.promotion_management_system.service.user.UserService;
import com.example.promotion_management_system.util.UriMapping;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(UriMapping.ADMIN_BASE_URL)
@AllArgsConstructor
@RestController
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userDTO) {
        logger.info("Starting to create user: {}",userDTO);
        UserResponseDTO response = userService.register(userDTO);
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        logger.info("Starting to fetch all users");
        List<UserResponseDTO> response = userService.getAllUsers();
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO user) {
        logger.info("Starting to update user with id {}: {}", id, user);
        UserResponseDTO response = userService.updateUser(id, user);
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("Starting to delete user with id: {}", id);
        userService.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        logger.info("Starting to fetch user with id: {}", id);
        return userService.getUserById(id);
    }

}