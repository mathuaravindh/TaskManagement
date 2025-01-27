package org.airtribe.taskmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String role; // Enum: PROJECT_OWNER, TEAM_MANAGER, TEAM_MEMBER

    private String teamName; // Optional: The name of the team the user belongs to

    private Set<String> taskNames; // Optional: Names of tasks assigned to the user
}

