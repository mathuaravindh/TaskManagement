package org.airtribe.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_owner_id")
    private User projectOwner; // The user who owns the team

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private User leader; // The user leading the team

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<User> members; // All users assigned to this team

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Task> tasks; // Tasks within the team
}

