package com.taskflow.taskmanagement.dtos.demand.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandRequest {


    @NotNull(message = "The Task id can not be null")
    private Long taskId;

    private String description;

}
