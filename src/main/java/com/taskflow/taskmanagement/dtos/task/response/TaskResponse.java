package com.taskflow.taskmanagement.dtos.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskflow.taskmanagement.costumValidations.EnumValue;
import com.taskflow.taskmanagement.costumValidations.ThreeDaysMaxFromNow;
import com.taskflow.taskmanagement.dtos.authentication.response.UserResponse;
import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private UserResponse createdBy;

    private UserResponse assignedBy;

    private UserResponse assignedTo;

    private TaskStatus status;

    private List<Tag> tags;
}
