package org.airtribe.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.airtribe.taskmanagement.enums.Role;

import java.util.Set;

@Entity
@Data // Lombok generates getters, setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Enum: PROJECT_OWNER, TEAM_MANAGER, TEAM_MEMBER

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // The team this user belongs to

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private Set<Task> assignedTasks; // Tasks assigned to this user
}

