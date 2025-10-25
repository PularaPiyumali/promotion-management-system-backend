package com.example.promotion_management_system.controller;

import com.example.promotion_management_system.model.user.UserLoginRequestDTO;
import com.example.promotion_management_system.model.user.UserLoginResponseDTO;
import com.example.promotion_management_system.service.user.UserService;
import com.example.promotion_management_system.util.UriMapping;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(UriMapping.USER_BASE_URL)
@AllArgsConstructor
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @PostMapping(UriMapping.USER_LOGIN)
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO userDTO) {
        logger.info("User login attempt: {}", userDTO.getUsername());
        UserLoginResponseDTO response = userService.login(userDTO);
        return response;
    }

}
