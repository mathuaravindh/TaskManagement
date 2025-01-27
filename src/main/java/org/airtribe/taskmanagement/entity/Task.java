package org.airtribe.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.airtribe.taskmanagement.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Status status; // Enum: OPEN, IN_PROGRESS, COMPLETED

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
}

