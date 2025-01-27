package org.airtribe.taskmanagement.dto;

import lombok.Data;
import org.airtribe.taskmanagement.enums.Role;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Role role;
}
