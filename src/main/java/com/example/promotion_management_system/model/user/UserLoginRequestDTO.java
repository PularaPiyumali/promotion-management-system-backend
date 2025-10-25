package com.example.promotion_management_system.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * The type User dto.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRequestDTO {

    private String username;

    private String password;
}
