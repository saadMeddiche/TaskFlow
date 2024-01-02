package com.taskflow.taskmanagement.converters;

import com.taskflow.taskmanagement.dtos.demand.request.DemandRequest;
import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.enums.DemandStatus;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DemandConverter {

    private final TaskService taskService;

    private final AuthenticationService authenticationService;

    public DemandReplacement convertToEntity(DemandRequest demandRequest) {
        return DemandReplacement.builder()
                .id(null)
                .description(demandRequest.getDescription())
                .dateDemand(LocalDateTime.now())
                .task(taskService.getById(demandRequest.getTaskId()))
                .demandedBy(authenticationService.getCurrentAuthenticatedUser())
                .status(DemandStatus.PENDING)
                .build();
    }
}
