package org.airtribe.taskmanagement.service;

import org.airtribe.taskmanagement.entity.Team;
import org.airtribe.taskmanagement.entity.User;
import org.airtribe.taskmanagement.repository.TeamRepository;
import org.airtribe.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public Team createTeam(String teamName) {
        // Check if the team already exists
        Optional<Team> existingTeam = teamRepository.findByName(teamName);
        if (existingTeam.isPresent()) {
            throw new RuntimeException("Team with this name already exists.");
        }

        Team team = new Team();
        team.setName(teamName);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public Team updateTeam(Long id, String newTeamName) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        team.setName(newTeamName);
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public User assignUserToTeam(Long userId, Long teamId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        user.setTeam(team);
        return userRepository.save(user);
    }
}

