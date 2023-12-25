package com.taskflow.taskmanagement.dtos.task.request;

import com.taskflow.taskmanagement.costumValidations.ThreeDaysMaxFromNow;
import com.taskflow.taskmanagement.entities.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

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

    @NotNull(message = "Ids of tags of task cannot be null")
    @NotEmpty(message = "Ids of tags of task cannot be empty")
    private List<Long> tagsId;

}
