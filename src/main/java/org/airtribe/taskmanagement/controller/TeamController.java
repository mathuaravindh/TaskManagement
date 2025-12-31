package org.airtribe.taskmanagement.controller;

import org.airtribe.taskmanagement.entity.Team;
import org.airtribe.taskmanagement.entity.User;
import org.airtribe.taskmanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Create Team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestParam String teamName) {
        Team team = teamService.createTeam(teamName);
        return ResponseEntity.status(201).body(team);
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    // Get team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    // Update team
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestParam String newTeamName) {
        Team updatedTeam = teamService.updateTeam(id, newTeamName);
        return ResponseEntity.ok(updatedTeam);
    }

    // Delete team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{teamId}/add-user/{userId}")
    public ResponseEntity<User> assignUserToTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        User updatedUser = teamService.assignUserToTeam(userId, teamId);
        return ResponseEntity.ok(updatedUser);
    }
}
