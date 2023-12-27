package com.taskflow.taskmanagement.dtos.demand.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandRequest {


    @NotNull(message = "The current Task id can not be null")
    private Long currentTask_id;

    @NotNull(message = "The new Task id can not be null")
    private Long newTask_id;

    private String description;

}
