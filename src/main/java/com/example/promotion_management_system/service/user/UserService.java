package com.example.promotion_management_system.service.user;

import com.example.promotion_management_system.model.user.UserLoginRequestDTO;
import com.example.promotion_management_system.model.user.UserLoginResponseDTO;
import com.example.promotion_management_system.model.user.UserRequestDTO;
import com.example.promotion_management_system.model.user.UserResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {

    UserLoginResponseDTO login(UserLoginRequestDTO userDTO);


    /**
     * Register user response dto.
     *
     * @param userDTO the user dto
     * @return the user response dto
     */
    @Transactional
    UserResponseDTO register(UserRequestDTO userDTO);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserResponseDTO> getAllUsers();


    /**
     * Update user response dto.
     *
     * @param id          the id
     * @param updatedUser the updated user
     * @return the user response dto
     */
    @Transactional
    UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser);

    /**
     * Delete user.
     *
     * @param id the id
     */
    void deleteUser(Long id);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserResponseDTO getUserById(Long id);
}
