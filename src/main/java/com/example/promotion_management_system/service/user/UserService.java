package com.example.promotion_management_system.service.user;

import com.example.promotion_management_system.config.JWTService;
import com.example.promotion_management_system.domain.UserRepository;
import com.example.promotion_management_system.domain.user.User;
import com.example.promotion_management_system.model.user.*;
import com.example.promotion_management_system.util.exception.PromotionManagementSystemException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    /**
     * Login user response dto.
     *
     * @param userDTO the user dto
     * @return the user response dto
     */
    public UserLoginResponseDTO login(UserLoginRequestDTO userDTO) {
        logger.info("User login: {}", userDTO);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        if (!authentication.isAuthenticated()) {
            logger.error("User login failed: {}", userDTO);
            throw new PromotionManagementSystemException(
                    "User Login Failed", HttpStatus.BAD_REQUEST.value());
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        UserLoginResponseDTO response = new UserLoginResponseDTO();
        response.setAccessToken(jwtService.generateToken(userDetails.getUsername(), role));
        response.setMessage("User Login Success");
        response.setRole(UserRole.valueOf(role));
        logger.info("User login successful: {}", userDTO);
        return response;
    }


    /**
     * Register user response dto.
     *
     * @param userDTO the user dto
     * @return the user response dto
     */
    @Transactional
    public UserResponseDTO register(UserRequestDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        logger.info("Creating user: {}", userDTO);

        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            logger.error("Error creating user, username already taken: {}", userDTO);
            throw new PromotionManagementSystemException(
                    "Username already taken!", HttpStatus.BAD_REQUEST.value());
        }

        User user = UserMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        response.setMessage("User registered successfully!");
        logger.info("Successfully created user: {}", response);
        return response;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<UserResponseDTO> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        return UserMapper.toUserResponseDtoList(users);
    }


    /**
     * Update user response dto.
     *
     * @param id          the id
     * @param updatedUser the updated user
     * @return the user response dto
     */
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO updatedUser) {
        logger.info("Updating user with id {}: {}", id, updatedUser);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new PromotionManagementSystemException("User not found", HttpStatus.BAD_REQUEST.value()));

        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getRole() != null && !updatedUser.getRole().isEmpty()) {
            user.setRole(updatedUser.getRole());
        }
        if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().isEmpty()) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isEmpty()) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPhoneNumber() != null && !updatedUser.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        User updatedUserDetails = userRepository.save(user);

        return UserMapper.toUserResponseDto(updatedUserDetails);
    }

    /**
     * Delete user.
     *
     * @param id the id
     */
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public UserResponseDTO getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new PromotionManagementSystemException("User not found", HttpStatus.NOT_FOUND.value());});

        UserResponseDTO response = UserMapper.toUserResponseDto(user);
        logger.info("Successfully fetched user: {}", response);
        return response;
    }
}
