package org.airtribe.taskmanagement.service;


import org.airtribe.taskmanagement.dto.UserDto;
import org.airtribe.taskmanagement.entity.Team;
import org.airtribe.taskmanagement.exception.custom.ResourceNotFoundException;
import org.airtribe.taskmanagement.mapper.UserMapper;
import org.airtribe.taskmanagement.repository.TaskRepository;
import org.airtribe.taskmanagement.repository.TeamRepository;
import org.airtribe.taskmanagement.repository.UserRepository;
import org.airtribe.taskmanagement.dto.UserRegistrationDto;
import org.airtribe.taskmanagement.entity.User;
import org.airtribe.taskmanagement.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User registerUser(UserRegistrationDto dto) {
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Find or create the team
        Optional<Team> team = null;
        if (dto.getTeamName() != null) {
            team = teamRepository.findByName(dto.getTeamName());
        }

        // Use the mapper to create a user entity
        User user = userMapper.mapToEntity(dto, team);

        // Save the user to the database
        return userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtTokenUtil.generateToken(user.getEmail());
            }
        }
        return null;
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update a user
    public User updateUser(Long id, UserDto userDto) {
        User existingUser = getUserById(id);
        userMapper.updateEntityFromDto(userDto, existingUser);
        return userRepository.save(existingUser);
    }

    // Delete a user
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}

