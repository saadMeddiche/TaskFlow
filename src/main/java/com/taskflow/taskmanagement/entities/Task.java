package com.taskflow.taskmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskflow.taskmanagement.enums.TaskStatus;
import com.taskflow.taskmanagement.validations.EnumValue;
import com.taskflow.taskmanagement.validations.ThreeDaysMaxFromNow;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name of task cannot be blank")
    @NotNull(message = "Name of task cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Start date of task cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @ThreeDaysMaxFromNow
    private LocalDate startDate;

    @NotNull(message = "End date of task cannot be null")
    private LocalDate endDate;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User assignedBy;

    @ManyToOne
    private User assignedTo;

    @EnumValue(enumClass = TaskStatus.class , message = "The Status Of Task Can Only Be 'Done' or 'UnDone' or 'InProgress'")
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @ManyToMany
    @JsonIgnoreProperties("tasks")
    private List<Tag> tags;

}