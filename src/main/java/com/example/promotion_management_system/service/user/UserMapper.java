package com.example.promotion_management_system.service.user;

import com.example.promotion_management_system.domain.user.User;
import com.example.promotion_management_system.model.user.UserRequestDTO;
import com.example.promotion_management_system.model.user.UserResponseDTO;
import com.example.promotion_management_system.model.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    /**
     * To user user.
     *
     * @param dto the dto
     * @return the user
     */
    public static User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    /**
     * To users list.
     *
     * @param userRequestDTOS the user request dtos
     * @return the list
     */
    public static List<User> toUsers(List<UserRequestDTO> userRequestDTOS) {
        List<User> users = new ArrayList<>();
        userRequestDTOS.forEach(userRequestDTO -> {
            users.add(toUser(userRequestDTO));
        });
        return users;
    }

    /**
     * To user response dto user response dto.
     *
     * @param user the user
     * @return the user response dto
     */
    public static UserResponseDTO toUserResponseDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(UserRole.valueOf(user.getRole()));
        return dto;
    }

    /**
     * To user response dto list list.
     *
     * @param users the users
     * @return the list
     */
    public static List<UserResponseDTO> toUserResponseDtoList(List<User> users) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        users.forEach(user -> {
            userResponseDTOS.add(toUserResponseDto(user));
        });

        return userResponseDTOS;
    }

    /**
     * Update user fields.
     *
     * @param existingUser    the existing user
     * @param dto             the dto
     * @param passwordEncoder the password encoder
     */
    public static void updateUserFields(User existingUser, UserRequestDTO dto, PasswordEncoder passwordEncoder) {
        if (dto.getUsername() != null) {
            existingUser.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRole() != null) {
            existingUser.setRole(dto.getRole());
        }
        if (dto.getFirstName() != null) {
            existingUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existingUser.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            existingUser.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(dto.getPhoneNumber());
        }
    }

}
