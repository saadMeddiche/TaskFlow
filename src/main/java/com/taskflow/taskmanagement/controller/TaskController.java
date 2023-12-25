package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.converters.TaskConverter;
import com.taskflow.taskmanagement.dtos.task.request.TaskAssignRequest;
import com.taskflow.taskmanagement.dtos.task.request.TaskRequest;
import com.taskflow.taskmanagement.dtos.task.response.TaskResponse;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.enums.TaskStatus;
import com.taskflow.taskmanagement.mappers.TaskMapper;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.TaskService;
import com.taskflow.taskmanagement.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskConverter taskConverter;


    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest) {

        Task task = taskConverter.convertToEntity(taskRequest);

        Task createdTask = taskService.createTask(task);

        TaskResponse createdTaskResponse = taskConverter.convertToTaskResponse(createdTask);

        return new ResponseEntity<>(createdTaskResponse , HttpStatus.CREATED);
    }

    @PostMapping("/assignTask")
    public ResponseEntity<?> assignTask(@Valid @RequestBody TaskAssignRequest taskRequest) {

        Task task = taskConverter.convertToEntity(taskRequest);

        taskService.assignTask(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"/assignAdditionalTask/{taskId}" , "/assignAdditionalTask/"} )
    public ResponseEntity<?> assignAdditionalTask(@Valid @PathVariable Long taskId) {

        Task task = taskConverter.convertToEntity(taskId);

        taskService.assignAdditionalTask(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
