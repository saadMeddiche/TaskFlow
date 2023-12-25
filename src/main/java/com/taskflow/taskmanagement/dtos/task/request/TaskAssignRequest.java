package com.taskflow.taskmanagement.dtos.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignRequest {

    @NotNull(message = "Task Id cannot be null")
    private Long taskId;

    @NotNull(message = "Assigned User Email cannot be null")
    @NotBlank(message = "Assigned User Email cannot be blank")
    private String assignedUserEmail ;


}
