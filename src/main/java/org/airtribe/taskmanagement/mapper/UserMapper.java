package org.airtribe.taskmanagement.mapper;

import org.airtribe.taskmanagement.dto.UserDto;
import org.airtribe.taskmanagement.dto.UserRegistrationDto;
import org.airtribe.taskmanagement.entity.Task;
import org.airtribe.taskmanagement.entity.Team;
import org.airtribe.taskmanagement.entity.User;
import org.airtribe.taskmanagement.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToEntity(UserRegistrationDto dto, Optional<Team> team) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(dto.getRole() != null)
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        else
            user.setRole(Role.TEAM_MEMBER);
        if(team != null)
            user.setTeam(team.get());

        // Map task names to tasks
        if (dto.getTaskNames() != null && !dto.getTaskNames().isEmpty()) {
            Set<Task> tasks = new HashSet<>();
            for (String taskName : dto.getTaskNames()) {
                Task task = new Task();
                task.setTitle(taskName);
                task.setAssignedTo(user);
                tasks.add(task);
            }
            user.setAssignedTasks(tasks);
        }
        return user;
    }

    public void updateEntityFromDto(UserDto userDto, User user) {
        if (userDto.getUsername() != null) user.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null) user.setPassword(userDto.getPassword()); // Ensure password is hashed
        if (userDto.getRole() != null) user.setRole(userDto.getRole());
    }
}

